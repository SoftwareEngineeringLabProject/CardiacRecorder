package com.example.firebase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;


public class FirebaseUnitTest {
    private  DataArrayList mockDataList(){
        DataArrayList dataArrayList =new DataArrayList();
        dataArrayList.addData(mockData());
        return  dataArrayList;
    }

    private Data mockData(){
        return  new Data("120","80","60","good","21-07-2022","9:28");
    }

    @Test
    public void testAddData(){
        DataArrayList dataArrayList=mockDataList();
        assertEquals(1,dataArrayList.getDatas().size());

        Data data=new Data
                ("160","60","50","Good","08-08-2022","05:22" );

        dataArrayList.addData(data);
        assertEquals(2,dataArrayList.getDatas().size());
        assertTrue(dataArrayList.getDatas().contains(data));
    }

    @Test
    public void testAddException(){
        DataArrayList cardiacArrayList=new DataArrayList();
        Data data=mockData();
        cardiacArrayList.addData(data);

        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                cardiacArrayList.addData(data);
            }
        });
    }

    @Test
    public void testGetDatas(){
        DataArrayList dataArrayList=mockDataList();
        assertEquals(0,mockData().compareTo(dataArrayList.getDatas().get(0)));

        Data d1= new Data
                ("190","60","50","not good","8-01-2022","05:22" );
        dataArrayList.addData(d1);

        Data d2= new Data
                ("90","60","50","good","6-01-2022","05:22" );
        dataArrayList.addData(d2);

        assertEquals(0,d1.compareTo(dataArrayList.getDatas().get(1)));
        assertEquals(0,mockData().compareTo(dataArrayList.getDatas().get(0)));
        assertEquals(0,d2.compareTo(dataArrayList.getDatas().get(2)));
    }

    @Test
    public void testdelete(){
        DataArrayList dataArrayList=new DataArrayList();
        Data d1= new Data
                ("190","60","50","not good","8-01-2022","05:22" );
        dataArrayList.addData(d1);

        Data d2= new Data
                ("90","60","50","good","6-01-2022","05:22" );
        dataArrayList.addData(d2);

        dataArrayList.delete(d1);
        assertTrue(!dataArrayList.getDatas().contains(d1));
    }

    @Test
    public void testDeleteException(){
        DataArrayList dataArrayList=new DataArrayList();
        Data d1= new Data
                ("190","60","50","not good","8-01-2022","05:22" );
        dataArrayList.addData(d1);

        Data d2= new Data
                ("90","60","50","good","6-01-2022","05:22" );
        dataArrayList.addData(d2);

        dataArrayList.delete(d1);
        dataArrayList.delete(d2);
        // trying to delete an element which is not in the list
        assertThrows(IllegalArgumentException.class,()->{
            dataArrayList.delete(d1);
        });
    }

    @Test
    public void testcount(){
        DataArrayList dataArrayList=new DataArrayList();
        Data d1= new Data
                ("190","60","50","not good","8-01-2022","05:22" );
        dataArrayList.addData(d1);

        Data d2= new Data
                ("90","60","50","good","6-01-2022","05:22" );
        dataArrayList.addData(d2);

        assertEquals(2,dataArrayList.count());
        dataArrayList.delete(d1);
        assertEquals(1,dataArrayList.count());
        dataArrayList.delete(d2);
        assertEquals(0,dataArrayList.count());
    }
}
