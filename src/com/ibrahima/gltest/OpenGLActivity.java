package com.ibrahima.gltest;


import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

import java.io.*;

public class OpenGLActivity extends Activity {

    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = new HelloOpenGLES20SurfaceView(this);
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume();
    }
}

class HelloOpenGLES20SurfaceView extends GLSurfaceView {

    public HelloOpenGLES20SurfaceView(Context context){
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);
        // Set the Renderer for drawing on the GLSurfaceView
        String vert = loadShader(R.raw.vertex);
        String frag = loadShader(R.raw.frag);
        setRenderer(new GLESRenderer(vert, frag));
    }
    String loadShader(int resourceID){
        InputStream is = getResources().openRawResource(resourceID);
        BufferedReader bi = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        try {
            while(bi.ready()){
                builder.append(bi.readLine());
            }
            is.close();
        } catch (IOException e) {
            Log.v("OGL", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return builder.toString();
    }
}
