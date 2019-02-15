package com.bawei.fersco;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView mfresco;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private RoundingParams parames;
    private GenericDraweeHierarchyBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfresco = findViewById(R.id.mfresco);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        mfresco.setImageURI(Uri.parse("https://m.360buyimg.com/n0/jfs/t6037/35/2944615848/95178/6cd6cff0/594a3a10Na4ec7f39.jpg!q70.jpg"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                builder = new GenericDraweeHierarchyBuilder(getResources());
                // 设置圆角图片
                //设置边角的弧度,使其为圆角
                parames = RoundingParams.fromCornersRadius(100f);
                /* //设置图片控件的背景颜色parames.setOverlayColor(getResources().getColor(android.R.color.holo_red_light));
                //覆盖层
                //设置图片的边框颜色及边框的粗细parames.setBorder(getResources().getColor(android.R.color.holo_blue_light),5);
                //边框*/
                //这里的代码和设置圆形图片这一块代码是一种的,唯一不同就是对parames的设置.
                GenericDraweeHierarchy circularBead = builder.setRoundingParams(parames).build();
                mfresco.setHierarchy(circularBead);
                // 加载图片
                mfresco.setImageURI(Uri.parse("https://m.360buyimg.com/n0/jfs/t6037/35/2944615848/95178/6cd6cff0/594a3a10Na4ec7f39.jpg!q70.jpg"));
                break;
            case R.id.btn2:
                // 设置圆形图片
                // 设置形状对象,形状为圆形
                builder = new GenericDraweeHierarchyBuilder(getResources());
                parames =RoundingParams.asCircle();
                //创建设置参数,设置一个形状,把形状对象塞入
                GenericDraweeHierarchy roundness= builder.setRoundingParams(parames).build();
                //将参数对象设置给图片控件
                mfresco.setHierarchy(roundness);
                mfresco.setImageURI(Uri.parse("https://m.360buyimg.com/n0/jfs/t6037/35/2944615848/95178/6cd6cff0/594a3a10Na4ec7f39.jpg!q70.jpg"));
                break;
            case R.id.btn3:
                ViewGroup.LayoutParams params = mfresco.getLayoutParams();
                params.width=300;
                params.height=300;
                mfresco.setLayoutParams(params);
                break;
            case R.id.btn4:
                Uri uri = Uri.parse("https://m.360buyimg.com/n0/jfs/t6037/35/2944615848/95178/6cd6cff0/594a3a10Na4ec7f39.jpg!q70.jpg");
// 加载质量配置,为了实现节省CPU,随着图片下载的进行，下载完的扫描序列如下: 1, 4, 5, 10
/* 首次调用getNextScanNumberToDecode返回为2，因为初始时，解码的扫描数为0。
那么1将不会解码，下载完成4个扫描时，解码一次。下个解码为扫描数为6(5不会解码，10才会解码)*/
                ProgressiveJpegConfig jpegConfig= new ProgressiveJpegConfig() {
                    @Override
                    public int getNextScanNumberToDecode(int scanNumber) {
                        return scanNumber + 2;
                    }

                    @Override
                    public QualityInfo getQualityInfo(int scanNumber) {
                        boolean isGoodEnough =(scanNumber >= 5);
                        return ImmutableQualityInfo.of(scanNumber,isGoodEnough, false);
                    }
                };
//上面的和下面一行是固定代码.使用使复制粘贴即可
                ImagePipelineConfig.newBuilder(MainActivity.this).setProgressiveJpegConfig(jpegConfig).build();
// 创建 ImageRequest 对象.
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)//设置URL
                        .setProgressiveRenderingEnabled(true)//打开渐进渲染
                        .build();
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        //必须要设置ImageRequest对象,里面包含了图片的网址.
                        .setImageRequest(request)
                        //开启用户点击重新加载图片的功能
                        .setTapToRetryEnabled(true)
                        //会复用以前的对象,可以节省内存.
                        .setOldController(mfresco.getController())
                        .build();
// 1设置加载的控制
                mfresco.setController(draweeController);
                break;
            case R.id.btn5:
                break;
            case R.id.btn6:
                Uri uris = Uri.parse("http://img02.sogoucdn.com/app/a/100520021/506d836d1e3b115e259f33788d89fcfa");
                DraweeController controller =Fresco.newDraweeControllerBuilder()
                        .setUri(uris)//设置GIF网址
                        .setAutoPlayAnimations(true)//是否自动播放动画,false为不播放
                        .setOldController(mfresco.getController())//内存优化
                        .build();
                mfresco.setController(controller);
                break;
            case R.id.btn7:
                Uri uria = Uri.parse("https://m.360buyimg.com/n0/jfs/t6037/35/2944615848/95178/6cd6cff0/594a3a10Na4ec7f39.jpg!q70.jpg");
                BaseControllerListener<ImageInfo> baseControllerListener = new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id,  ImageInfo imageInfo,Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);
                        Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        super.onFailure(id, throwable);
                        Toast.makeText(MainActivity.this,"下载失败",Toast.LENGTH_SHORT).show();
                    }
                };
                DraweeController controllers = Fresco.newDraweeControllerBuilder()
                        .setControllerListener(baseControllerListener)
                        .setUri(uria)
                        // other setters
                        .build();
                mfresco.setController(controllers);
                break;
            case R.id.btn8:
                Intent intent = new Intent(this, BaseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
