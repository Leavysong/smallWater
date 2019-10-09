package com.df.smallwater.smallwater.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;

import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.bean.Version;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DF on 2018/6/14.
 */

public class versionUtil {


    Context context;
    Version version;

    /**
     * 必须先执行
     *
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }


    //对比本程序的版本号和最新程序的版本号
    public void checkVersion(View view, Version v) throws Exception {//按钮！
        version = v;
        //如果检测本程序的版本号小于服务器的版本号，那么提示用户更新
        if (getVersionCode() < version.getVersioncode()) {
            showDialogUpdate();//弹出提示版本更新的对话框
            PreferencesUtils.putString(context, "versioncode", version.getVersionname());
        } else {
            //否则吐司，说现在是最新的版本

            PreferencesUtils.putString(context, "versioncode", "当前版本为最新版本");

        }
    }


    /*
 * 获取当前程序的版本名
 */
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        Log.e("TAG", "版本号" + packInfo.versionCode);
        Log.e("TAG", "版本名" + packInfo.versionName);
        return packInfo.versionName;
    }


    /*
 * 获取当前程序的版本号
 */
    private int getVersionCode() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        Log.e("TAG", "版本号" + packInfo.versionCode);
        Log.e("TAG", "版本名" + packInfo.versionName);
        return packInfo.versionCode;
    }


    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //设置点击对话框外部区域不关闭对话框
        builder.setCancelable(false);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置提示框的图标
                        setIcon(R.mipmap.logo).
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        loadNewVersionProgress();//下载最新的版本程序
                    }
                }).


                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (version.getForceupdate() == 1) {
                            System.exit(0);
                        } else {

                        }


                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }


    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final String uri = version.getDownurl();
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
//                    Toast.makeText(context, "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public static File getFileFromServer(String uri, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), time + "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }


    /**
     * 安装apk
     */
    protected void installApk(File file) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, "com.df.smallwater.smallwater.fileprovider", file);
            intent.setDataAndType(contentUri, context.getContentResolver().getType(contentUri));
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }


}
