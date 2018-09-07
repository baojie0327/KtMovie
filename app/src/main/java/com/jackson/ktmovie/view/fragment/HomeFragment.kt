package com.jackson.ktmovie.view.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.*
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.jackson.ktmovie.adapter.home.*
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.dagger.component.DaggerHomeComponent
import com.jackson.ktmovie.dagger.module.HomeModule
import com.jackson.ktmovie.presenter.HomePresenter
import com.jackson.ktmovie.utils.CommonMethod
import com.jackson.ktmovie.utils.Constant
import com.jackson.ktmovie.utils.LogUtil
import com.jackson.ktmovie.view.IView
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.find
import java.util.*
import javax.inject.Inject

/**
 * HomeFragment  2018-06-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 首页
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 19
 */
class HomeFragment : Fragment(), IView.IHomeView {

    private var rootView: View? = null
    private lateinit var llBack: LinearLayout  // 返回键，需要隐藏
    private lateinit var tvTitle: TextView   // title
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRefreshLayout: SmartRefreshLayout

    private lateinit var mAdapters: LinkedList<DelegateAdapter.Adapter<*>>
    private lateinit var delegateAdapter: DelegateAdapter
    private var dataList: MutableList<HomeBean.DataBean> = mutableListOf()


    // adapter
    private lateinit var mBannerAdapter: BannerAdapter  // Banner适配器
    private lateinit var mGridMenuAdapter: GridMenuAdapter // GridMenu
    private lateinit var mMarqueeAdapter: MarqueeAdapter // 公告适配器
    private lateinit var mGridMenuSpanAdapter: GridMenuSpanAdapter // Grid Span
    private lateinit var mHorizontalAdapter: HorizontalAdapter  // 水平
    private lateinit var mStickyAdapter: StickyAdapter  // 吸边布局
    private lateinit var mLinearAdapter: LinearAdapter  // 水平
    private lateinit var mFixAdapter: FixAdapter  // Fix
    private lateinit var mFloatAdapter: FloatAdapter  // folat
    private lateinit var mColumnAdapter: ColumnAdapter
    private lateinit var mOnePlusNAdapter: OnePlusNAdapter
    private lateinit var mStaggeredAdapter: StaggeredAdapter

    private lateinit var mScrollFixAdapter: ScrollFixAdapter // ScrollFix

    @Inject
    lateinit var mHomePresenter: HomePresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater!!.inflate(R.layout.fragment_home_layout, null)
            inject();
            initView()
            initData()
            mHomePresenter.getData(0)
            refreshListen()

        }
        return rootView
    }

    /**
     * inject
     */
    private fun inject() =
            DaggerHomeComponent.builder()
                    .homeModule(HomeModule(this))
                    .build()
                    .inject(this)


    /**
     * 初始化界面
     */
    private fun initView() {
        // 隐藏返回键
        llBack = rootView!!.find(R.id.ll_back)
        llBack.visibility = View.INVISIBLE
        // 设置title
        tvTitle = rootView!!.find(R.id.tv_head_title)
        tvTitle.text = "首页"
        mRefreshLayout = rootView!!.find(R.id.refreshLayout)
        // 设置RecyclerView
        mRecyclerView = rootView!!.find(R.id.recyclerView)
        /*   var layoutManger: LinearLayoutManager = LinearLayoutManager(activity)
          mRecyclerView.layoutManager = layoutManger*/
        mAdapters = LinkedList<DelegateAdapter.Adapter<*>>()
        // 刷新设置
        mRefreshLayout.autoRefresh()  // 开启自动刷新
        //  mRefreshLayout.setEnableAutoLoadMore(true); // 开启自动加载功能
        // mRefreshLayout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
        //  mRefreshLayout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        // mRefreshLayout.setEnableOverScrollBounce(false);//关闭越界回弹功能

        // 设置官方刷新主题
        mRefreshLayout.setRefreshHeader(MaterialHeader(activity))
        mRefreshLayout.setEnableHeaderTranslationContent(false)

        /* var list_f = mutableListOf(1, 2, 3, 4, 5, 6, 7)
         list_f.indices
                 .map { it.toString() + " my" }
                 .filter { it == "5 my" }
                 .forEach { LogUtil.d(it.toString()) }*/
    }

    private fun initData() {
        //初始化
        //创建VirtualLayoutManager对象
        val layoutManager = VirtualLayoutManager(context!!)
        layoutManager.setRecycleOffset(5000)
        // 将VirtualLayoutManager绑定到recyclerView
        mRecyclerView.layoutManager = layoutManager

        //设置组件复用回收池,（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        val viewPool = RecyclerView.RecycledViewPool()
        mRecyclerView.recycledViewPool = viewPool
        //  mRecyclerView.addItemDecoration(itemDecoration);
        viewPool.setMaxRecycledViews(0, 20)

        delegateAdapter = DelegateAdapter(layoutManager, true)
        mRecyclerView.adapter = delegateAdapter
    }

    override fun setData(mDataList: List<HomeBean.DataBean>) {
        LogUtil.d("size==" + mDataList.size)
        if (dataList.isNotEmpty()) {
            dataList.clear()
        }
        dataList = mDataList.toMutableList()
        setBannerData(dataList[0].items!!)
        setGridMenu(dataList[1].items!!)
        setScrollFix()
        setMarquee(dataList[2].items!!)
        setGridSpan(dataList[3].items!!)
        setHorizon(dataList[4].items!!)
        setSticky()
        setLinear(dataList[5].items!!)
        setFix()
        setFloat()
        setColumn(dataList[4].items!!)
        setOnePlusN(dataList[6].items!!)
        setStaggered(dataList[7].items!!)
        setAllData()
    }

    private fun refreshListen() {
        mRefreshLayout.setOnRefreshListener {
            mRefreshLayout.layout.postDelayed(Runnable {
                mHomePresenter.getData(1)
            }, 2000)
        }

        mRefreshLayout.setOnLoadMoreListener {
            mRefreshLayout.layout.postDelayed(Runnable {
                mRefreshLayout.finishLoadMoreWithNoMoreData()  //将不会再次触发加载更多事件
                mRefreshLayout.finishLoadMore()
            }, 2000)
        }
    }

    override fun onRefresh(mDataList: List<HomeBean.DataBean>) {
        if (mAdapters.size > 0) {
            mAdapters.clear()
        }
        setData(mDataList)
        mRecyclerView.adapter.notifyDataSetChanged()
        mRefreshLayout.finishRefresh()
        mRefreshLayout.setNoMoreData(false)
    }

    /**
     * 设置轮播
     */
    private fun setBannerData(items: List<HomeBean.DataBean.ItemsBean>) {
        val singleLayoutHelper: SingleLayoutHelper = SingleLayoutHelper()
        var imgList: MutableList<String> = mutableListOf()
        items.indices.mapTo(imgList) { "http:" + items[it].img }
        mBannerAdapter = BannerAdapter(context!!, imgList, singleLayoutHelper, R.layout.item_home_banner_layout, 1, Constant.typeBanner)
        mBannerAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)


        })
    }

    /**
     * GridMenu
     */
    private fun setGridMenu(items: List<HomeBean.DataBean.ItemsBean>) {
        val gridLayoutHelper: GridLayoutHelper = GridLayoutHelper(5)
        //  gridLayoutHelper.setMarginTop(300);  //设置距离顶部的距离
        //  gridLayoutHelper.setItemCount(30);  // 设置布局里Item个数
        gridLayoutHelper.setPadding(0, 0, 0, 0) // 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        // gridLayoutHelper.setMargin(20,10,20,10);   //设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //  gridLayoutHelper.setAspectRatio(6); // 设置设置布局内每行布局的宽与高的比
        //  gridLayoutHelper.setWeights(new float[]{40, 10, 20,20,10});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.vGap = 16  // 控制子元素之间的垂直间距
        gridLayoutHelper.hGap = 0  // 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false)//是否自动填充空白区域
        //   gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        gridLayoutHelper.bgColor = Color.WHITE // 设置背景颜色
        mGridMenuAdapter = GridMenuAdapter(context!!, items, gridLayoutHelper, R.layout.item_home_gridmenu_layout, items.size, Constant.typeGvidMenu)
        // 监听
        mGridMenuAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)
        })

        mGridMenuAdapter.setOnItemChildClickListener(object : BaseDelegateAdapter.OnItemChildClickListener {
            override fun onItemChildClick(view: View, position: Int) {
                when (view.id) {
                    R.id.img_grid_menu -> CommonMethod.showToast(context!!, "click-child-" + position, false)
                }
            }

        })

    }


    /**
     * 设置公告
     */
    private fun setMarquee(items: List<HomeBean.DataBean.ItemsBean>) {
        val linearLayoutHelper: LinearLayoutHelper = LinearLayoutHelper()
        var infoList: MutableList<String> = mutableListOf()
        items.indices.mapTo(infoList) { items[it].title!! }
        mMarqueeAdapter = MarqueeAdapter(context!!, infoList, linearLayoutHelper, R.layout.item_home_marquee_layout, 1, Constant.typeMarquee)
        mMarqueeAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)

        })
    }

    /**
     * GridSpan
     */
    private fun setGridSpan(items: List<HomeBean.DataBean.ItemsBean>) {
        val gridLayoutHelper = GridLayoutHelper(4)
        // gridLayoutHelper.setPadding(0, 30, 0, 30); // 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //    gridLayoutHelper.setVGap(10);  // 控制子元素之间的垂直间距
        //  gridLayoutHelper.setHGap(10);  // 控制子元素之间的水平间距
        //  gridLayoutHelper.setMargin(0,20,0,20);
        gridLayoutHelper.setAutoExpand(false)//是否自动填充空白区域
        gridLayoutHelper.bgColor = Color.WHITE // 设置背景颜色

        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        val count = mBannerAdapter.itemCount + mGridMenuAdapter.itemCount + mMarqueeAdapter.itemCount + mScrollFixAdapter.itemCount

        gridLayoutHelper.setSpanSizeLookup(object : GridLayoutHelper.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = if (position <= count + 1) {
                2
            } else {
                1
            }
        })

        mGridMenuSpanAdapter = GridMenuSpanAdapter(context!!, items, gridLayoutHelper, R.layout.item_home_gridspan_layout, items.size, Constant.typeHot)
        mGridMenuSpanAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)

        })

    }

    /**
     * 设置水平滑动的
     */
    private fun setHorizon(items: List<HomeBean.DataBean.ItemsBean>) {
        val singleLayoutHelper = SingleLayoutHelper()
        mHorizontalAdapter = HorizontalAdapter(context!!, items, singleLayoutHelper, R.layout.item_home_horizon_layout, 1, Constant.typePrepare)
        // 监听
        mHorizontalAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)

        })
    }

    /**
     * 设置Sticky布局，设置吸边布局
     */
    private fun setSticky() {
        val stickyLayoutHelper = StickyLayoutHelper()
        // 公共属性
        stickyLayoutHelper.itemCount = 1// 设置布局里Item个数
        //  stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //    stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //   stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        // stickyLayoutHelper.setAspectRatio(2);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        stickyLayoutHelper.setStickyStart(true)
        // true = 组件吸在顶部
        // false = 组件吸在底部

        stickyLayoutHelper.setOffset(100)// 设置吸边位置的偏移量
        val list = ArrayList<String>()
        mStickyAdapter = StickyAdapter(context!!, list, stickyLayoutHelper, R.layout.item_home_sticky_layout, 1, Constant.typeSticky)

    }

    /**
     * 设置水平布局
     */
    private fun setLinear(items: List<HomeBean.DataBean.ItemsBean>) {
        val linearLayoutHelper = LinearLayoutHelper()
        linearLayoutHelper.aspectRatio = 4.0f
        linearLayoutHelper.setDividerHeight(5)
        linearLayoutHelper.setMargin(0, 0, 0, 0)
        linearLayoutHelper.setPadding(0, 0, 0, 10)
        mLinearAdapter = LinearAdapter(context!!, items, linearLayoutHelper, R.layout.item__home_linear_layout, items.size, Constant.typeChoice)
        // 监听
        mLinearAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)

        })

        mLinearAdapter.setOnItemChildClickListener(object : BaseDelegateAdapter.OnItemChildClickListener {
            override fun onItemChildClick(view: View, position: Int) {
                when (view.id) {
                    R.id.img_choice_menu -> CommonMethod.showToast(context!!, "click-image-" + position, false)
                    R.id.tv_choice_name -> CommonMethod.showToast(context!!, "click-text-" + position, false)
                }
            }
        })
    }

    private fun setFix() {
        val fixLayoutHelper = FixLayoutHelper(FixLayoutHelper.TOP_LEFT, 80, 60)
        fixLayoutHelper.itemCount = 1// 设置布局里Item个数
        fixLayoutHelper.setPadding(20, 20, 20, 20)// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        fixLayoutHelper.setMargin(20, 20, 20, 20)// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        fixLayoutHelper.bgColor = Color.GRAY// 设置背景颜色
        //  fixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // fixLayoutHelper特有属性
        fixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT)// 设置吸边时的基准位置(alignType)
        fixLayoutHelper.setX(10)// 设置基准位置的横向偏移量X
        fixLayoutHelper.setY(10)// 设置基准位置的纵向偏移量Y
        val list = ArrayList<String>()
        mFixAdapter = FixAdapter(context!!, list, fixLayoutHelper, R.layout.item_home_fix_layout, 1, Constant.typeFix)
        mFixAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)


        })
    }

    private fun setFloat() {
        val floatLayoutHelper = FloatLayoutHelper()
        /* floatLayoutHelper.setItemCount(1);// 设置布局里Item个数
        floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        floatLayoutHelper.setBgColor(Color.RED);// 设置背景颜色*/
        //  floatLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // floatLayoutHelper特有属性
        floatLayoutHelper.setDefaultLocation(300, 300)// 设置布局里Item的初始位置

        val list = ArrayList<String>()
        mFloatAdapter = FloatAdapter(context!!, list, floatLayoutHelper, R.layout.item_home_float_layout, 1, Constant.typeFloat)
        mFloatAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)


        })

    }

    private fun setColumn(items: List<HomeBean.DataBean.ItemsBean>) {
        val columnLayoutHelper = ColumnLayoutHelper()
        // 公共属性
        columnLayoutHelper.itemCount = 3// 设置布局里Item个数
        //  columnLayoutHelper.setPadding(0, 0, 0, 10);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        columnLayoutHelper.setMargin(0, 0, 0, 10)// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //  columnLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        columnLayoutHelper.aspectRatio = 2.8.toFloat()// 设置设置布局内每行布局的宽与高的比
        columnLayoutHelper.paddingBottom = 10
        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(floatArrayOf(40f, java.lang.Float.NaN, 40f))// 设置该行每个Item占该行总宽度的比例
        mColumnAdapter = ColumnAdapter(context!!, items, columnLayoutHelper, R.layout.item_home_column_layout, 3, Constant.typeColumn)
        mColumnAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)


        })
    }

    private fun setOnePlusN(items: List<HomeBean.DataBean.ItemsBean>) {
        val onePlusNLayoutHelper = OnePlusNLayoutHelper(3)
        // 在构造函数里传入显示的Item数
        // 最多是1拖4,即5个
        // 公共属性
        onePlusNLayoutHelper.itemCount = 3// 设置布局里Item个数
        //  onePlusNLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //  onePlusNLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //  onePlusNLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        // onePlusNLayoutHelper.setColWeights(new float[]{40f, 50f, 50f});
        onePlusNLayoutHelper.aspectRatio = 2f// 设置设置布局内每行布局的宽与高的比
        mOnePlusNAdapter = OnePlusNAdapter(context!!, items, onePlusNLayoutHelper, R.layout.item_home_oneplusn_layout, 3, Constant.typeOnePlusN)
        mOnePlusNAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)

        })
    }

    /**
     * ScrollFix
     */
    private fun setScrollFix() {
        val scrollFixLayoutHelper: ScrollFixLayoutHelper = ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT, 80, 60)
        // 公共属性
        scrollFixLayoutHelper.itemCount = 1// 设置布局里Item个数
        scrollFixLayoutHelper.setPadding(20, 20, 20, 20)// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20)// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        scrollFixLayoutHelper.bgColor = Color.GRAY// 设置背景颜色
        //  scrollFixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.TOP_RIGHT)// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(10)// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(10)// 设置基准位置的纵向偏移量Y
        scrollFixLayoutHelper.showType = ScrollFixLayoutHelper.SHOW_ON_LEAVE// 设置Item的显示模式

        val list: List<String> = arrayListOf()
        mScrollFixAdapter = ScrollFixAdapter(context!!, list, scrollFixLayoutHelper, R.layout.item_home_fix_layout, 1, Constant.typeScrollFix)
        mScrollFixAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)


        })
    }

    private fun setStaggered(items: List<HomeBean.DataBean.ItemsBean>) {
        val staggeredGridLayoutHelper = StaggeredGridLayoutHelper(2)
        // 公有属性
        staggeredGridLayoutHelper.itemCount = 10// 设置布局里Item个数
        //  staggeredGridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        //    staggeredGridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //   staggeredGridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //   staggeredGridLayoutHelper.setAspectRatio(1.0f);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        staggeredGridLayoutHelper.lane = 2// 设置控制瀑布流每行的Item数
        //  staggeredGridLayoutHelper.setHGap(10);// 设置子元素之间的水平间距
        //  staggeredGridLayoutHelper.setVGap(15);// 设置子元素之间的垂直间距
        mStaggeredAdapter = StaggeredAdapter(context!!, items, staggeredGridLayoutHelper, R.layout.item_home_staggered_layout, items.size, Constant.typeStaggered)
        mStaggeredAdapter.setOnItemClickListener(object : BaseDelegateAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) =
                    CommonMethod.showToast(context!!, "click--" + position, false)


        })
    }

    private fun setAllData() {
        mAdapters.add(mBannerAdapter)
        mAdapters.add(mGridMenuAdapter)
        mAdapters.add(mScrollFixAdapter)
        mAdapters.add(mMarqueeAdapter)
        mAdapters.add(mGridMenuSpanAdapter)
        mAdapters.add(mHorizontalAdapter)
        mAdapters.add(mStickyAdapter)
        mAdapters.add(mLinearAdapter)
        mAdapters.add(mFixAdapter)
        mAdapters.add(mFloatAdapter)
        mAdapters.add(mColumnAdapter)
        mAdapters.add(mOnePlusNAdapter)
        mAdapters.add(mStaggeredAdapter)
        delegateAdapter.setAdapters(mAdapters)
    }

    override fun closeDispose(disposable: Disposable) = Unit


    /**
     * 伴生对象，提供Fragment的实例
     */
    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }

    }


}