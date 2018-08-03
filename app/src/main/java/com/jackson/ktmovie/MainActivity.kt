package com.jackson.ktmovie

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.jackson.ktmovie.view.fragment.HomeFragment
import com.jackson.ktmovie.view.fragment.HotShowFragment
import com.jackson.ktmovie.view.fragment.MineFragment
import com.jackson.ktmovie.view.fragment.RankListFragment
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), View.OnClickListener, BottomNavigationBar.OnTabSelectedListener {


    private lateinit var mflContent: FrameLayout    //布局
    private lateinit var mBottomNavigationBar: BottomNavigationBar  //BottomBar控件
    private lateinit var mTextBadgeItem: TextBadgeItem //  item提示文字
    private lateinit var mShapeBadgeItem: ShapeBadgeItem //item提示小圆点
    private var lastSelectedPosition: Int = 0

    // 三个Fragment
    private var mHomeFragment: HomeFragment? = null
    private var mHotShowFragment: HotShowFragment? = null
    private var mRankListFragment: RankListFragment? = null
    private var mMineFragment: MineFragment? = null

    // Fragment 管理器和执行器
    private lateinit var mManger: FragmentManager
    private lateinit var mTransaction: FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setDefaultFragment()

    }

    /**
     * 初始化界面view
     */
    private fun initView() {
        // 初始化控件
        mflContent = find(R.id.ll_content)
        mBottomNavigationBar = find(R.id.bottom_navigation_bar)
        // TODO设置模式
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
        // TODO设置背景色样式
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        mBottomNavigationBar.setBarBackgroundColor(R.color.background_gray_color)

        // 设置item提示文字
        mTextBadgeItem = TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.main_color)
                .setText("5")
                .setTextColorResource(R.color.white)
                .setBorderColorResource(R.color.colorPrimaryDark)  // 外边界颜色
                .setHideOnSelect(false)

        // 设置item上提示小圆点
        mShapeBadgeItem = ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColor(R.color.main_color)
                .setShapeColorResource(R.color.main_color)
                .setSizeInDp(this, 10, 10)
                .setEdgeMarginInDp(this, 2)
                //     .setSizeInPixels(30,30)
                //    .setEdgeMarginInPixels(-1)
                .setGravity(Gravity.TOP or Gravity.END)
                .setHideOnSelect(false)

        mBottomNavigationBar.addItem(BottomNavigationItem(R.mipmap.tab_home_pressed, "首页").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_home_normal).setInActiveColorResource(R.color.icon_color))
                .addItem(BottomNavigationItem(R.mipmap.tab_hotshow, "热映").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_hotshow_off).setInActiveColorResource(R.color.icon_color))
                .addItem(BottomNavigationItem(R.mipmap.tab_ranklist_check, "排行").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_ranklist_normal).setInActiveColorResource(R.color.icon_color).setBadgeItem(mShapeBadgeItem))
                .addItem(BottomNavigationItem(R.mipmap.tab_mine_pressed, "我的").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_mine_normal).setInActiveColorResource(R.color.icon_color).setBadgeItem(mTextBadgeItem))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise()

        // tab切换监听
        mBottomNavigationBar.setTabSelectedListener(this)

    }

    /**
     * 设置默认的Fragment
     */
    private fun setDefaultFragment() {
        mHomeFragment = HomeFragment.newInstance()
        mManger = supportFragmentManager
        mTransaction = mManger.beginTransaction()
        mTransaction.add(R.id.ll_content, mHomeFragment)
        mTransaction.commit()
    }


    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabSelected(position: Int) {
        lastSelectedPosition = position
        // 开启事务
        mTransaction = mManger.beginTransaction()
        hideFragment(mTransaction)
        /**
         * fragment 用 add + show + hide 方式
         * 只有第一次切换会创建fragment，再次切换不创建
         *
         * fragment 用 replace 方式
         * 每次切换都会重新创建
         *
         */
        when (position) {
            0 -> if (mHomeFragment == null) {
                mHomeFragment = HomeFragment.newInstance()
                mTransaction.add(R.id.ll_content, mHomeFragment)
            } else {
                mTransaction.show(mHomeFragment)
            }

            1 -> if (mHotShowFragment == null) {
                mHotShowFragment = HotShowFragment.newInstance()
                mTransaction.add(R.id.ll_content, mHotShowFragment)
            } else {
                mTransaction.show(mHotShowFragment)
            }

            2 -> if (mRankListFragment == null) {
                mRankListFragment = RankListFragment.newInstance()
                mTransaction.add(R.id.ll_content, mRankListFragment)
            } else {
                mTransaction.show(mRankListFragment)
            }

            3 -> if (mMineFragment == null) {
                mMineFragment = MineFragment.newInstance()
                mTransaction.add(R.id.ll_content, mMineFragment)
            } else {
                mTransaction.show(mMineFragment)
            }

        }
        // 事务提交
        mTransaction.commit()

    }

    override fun onTabReselected(position: Int) = Unit

    override fun onTabUnselected(position: Int) = Unit

    /**
     * 隐藏当前Fragment
     */
    private fun hideFragment(transaction: FragmentTransaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment)
        }

        if (mHotShowFragment != null) {
            transaction.hide(mHotShowFragment)
        }

        if (mRankListFragment != null) {
            transaction.hide(mRankListFragment)
        }

        if (mMineFragment != null) {
            transaction.hide(mMineFragment)
        }
    }


}
