package debug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.xiaodou.common.MainApplication;
import com.xiaodou.module_my.R;


public class MyAssetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_assets);
        MainApplication instance = MainApplication.getInstance();
        Toast.makeText(instance,"wode",Toast.LENGTH_SHORT).show();
    }
}
