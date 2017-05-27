package com.example.test.activity;

import com.example.test.models.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by liubin on 2017/5/25.
 */
@RunWith(MockitoJUnitRunner.class)
public class PresenterListTest {

    IListActivity.IView iView;

    PresenterList presenterList;

    @Before
    public void setUp() throws Exception {
        iView = mock(ListActivity.class);
        presenterList = new PresenterList(iView);
    }

    @Test
    public void loadData() throws Exception {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("pg","0");
        hashMap.put("sz","20");

        presenterList.loadData(hashMap);
        ArgumentCaptor<ArrayList> captor = ArgumentCaptor.forClass(ArrayList.class);

        verify(iView).onSuccess(captor.capture());

        ArrayList response = captor.getValue();

        assertEquals(response.size(), Database.ALL_BOOKS.size());
    }

}