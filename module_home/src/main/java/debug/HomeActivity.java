package debug;

import android.os.Bundle;

import com.lhz.android.libBaseCommon.seniorMvp.annotations.CreatePresenterAnnotation;
import com.xiaodou.module_home.R;
import com.xiaodou.module_home.base.BaseHomeActivity;
import com.xiaodou.module_home.contract.IHomeFragmentContract;
import com.xiaodou.module_home.presenter.HomeFragmentPresenter;

@CreatePresenterAnnotation(HomeFragmentPresenter.class)
public class HomeActivity extends BaseHomeActivity implements IHomeFragmentContract.View{

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
