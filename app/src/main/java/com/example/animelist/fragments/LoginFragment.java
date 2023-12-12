package com.example.animelist.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.animelist.R;
import com.example.animelist.activity.HomeActivity;
import com.example.animelist.utilities.ApiServiceSingleton;
import com.example.animelist.utilities.Constants;
import com.example.animelist.utilities.Utilities;

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            WebView webView = activity.findViewById(R.id.loginWebView);
            setWebView(webView);
            Log.e("TAG", "onViewCreated: " + Constants.AUTHORIZATION_URL +
                    "?client_id=" + Constants.CLIENT_ID +
                    "&response_type=token");
            webView.loadUrl(
                    Constants.AUTHORIZATION_URL +
                    "?client_id=" + Constants.CLIENT_ID +
                    "&response_type=token"
            );

        }
    }

    private void setWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(Constants.REDIRECT_URL)) {
                    String accessToken = url.substring(
                            Constants.REDIRECT_URL.length() + "#access_token=".length(),
                            url.indexOf("&")
                    );
                    ApiServiceSingleton.setAccessToken(accessToken);
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    Log.e("idk", "Home activity called from login activity: ");
                    startActivity(intent);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
