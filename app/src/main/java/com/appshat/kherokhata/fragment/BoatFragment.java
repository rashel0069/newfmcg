package com.appshat.kherokhata.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.net.http.*;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.appshat.kherokhata.R;
public class BoatFragment extends Fragment {
    WebView webView;
    ProgressBar progressBar;
    private String webUrl = "http://digitalistic.co:3000/lite/kherokhata---akkas/?m=channel-web&v=Fullscreen&options=%7B%22hideWidget%22%3Atrue%2C%22config%22%3A%7B%22enableReset%22%3Atrue%2C%22enableTranscriptDownload%22%3Atrue%7D%7D";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boat, container, false);
        webView = view.findViewById(R.id.webView_id);
        progressBar = view.findViewById(R.id.progressBar_id);
        webView.setWebViewClient(new AppWebViewClints(progressBar));
        webView.loadUrl(webUrl);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.suppressLayout(false);

        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        return view;
    }
    public class AppWebViewClints extends WebViewClient{
        private ProgressBar progressBar;
        public AppWebViewClints(ProgressBar progressBar){
            this.progressBar = progressBar;
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            super.shouldOverrideUrlLoading(view, url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }
}