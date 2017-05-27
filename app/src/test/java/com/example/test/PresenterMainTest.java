package com.example.test;

import com.example.test.activity.IMainActivity;
import com.example.test.activity.MainActivity;
import com.example.test.activity.PresenterMain;
import com.example.test.models.BaseResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by liubin on 2017/5/23.
 */
@RunWith(MockitoJUnitRunner.class)
public class PresenterMainTest {

    PresenterMain presenterMain;
    IMainActivity.IView iView;

    @Before
    public void setUp() throws Exception{
        iView = mock(MainActivity.class);
        presenterMain = new PresenterMain(iView);
    }

    @Test
    public void releaseNews() throws Exception {
        assertNotNull(presenterMain);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("title","title");
        hashMap.put("content","content");
        hashMap.put("price","100");
        presenterMain.releaseNews(hashMap);

        verify(iView).getNewsParams(hashMap);

        ArgumentCaptor<BaseResponse> captor = ArgumentCaptor.forClass(BaseResponse.class);

        verify(iView).showSuccess(captor.capture());

        BaseResponse response = captor.getValue();

        assertEquals(1,response.getCode());

    }

}