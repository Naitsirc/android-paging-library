package es.devtr.paging;


import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.ArrayList;
import java.util.List;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    public static final int PAGE_SIZE = 50;
    private static final int FIRST_PAGE = 1;
    private static final Long TIME = 5000L;



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {

        android.util.Log.v("LoadInitial","=>");

        new Thread(new Runnable() {
            @Override
            public void run() {

                int inicio = 0;

                List<Item> items = new ArrayList<Item>();

                for(int i=0;i<PAGE_SIZE;i++){

                    Item item = new Item();
                    item.index = ""+(i+inicio);

                    items.add(item);

                }

                try{
                    Thread.sleep(TIME);
                }catch (Exception ignored){}

                callback.onResult(items, null, FIRST_PAGE+1);
            }
        }).start();

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

        android.util.Log.v("LoadBefore","=>"+params.key);


        new Thread(new Runnable() {
            @Override
            public void run() {

                int inicio = (params.key-1)*PAGE_SIZE;
                boolean previous = (params.key > 1);
                Integer previousPage = previous ? params.key -1 : null;

                List<Item> items = new ArrayList<Item>();

                for(int i=0;i<PAGE_SIZE;i++){

                    Item item = new Item();
                    item.index = ""+(i+inicio);

                    items.add(item);

                }

                try{
                    Thread.sleep(TIME);
                }catch (Exception ignored){}

                callback.onResult(items, previousPage);
            }
        }).start();

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

        android.util.Log.v("LoadAfter","=>"+params.key);

        new Thread(new Runnable() {
            @Override
            public void run() {

                int inicio = (params.key-1)*PAGE_SIZE;
                boolean next = true;
                Integer nextPage = next ? params.key+1:null;

                List<Item> items = new ArrayList<Item>();

                for(int i=0;i<PAGE_SIZE;i++){

                    Item item = new Item();
                    item.index = ""+(i+inicio);

                    items.add(item);

                }

                try{
                    Thread.sleep(TIME);
                }catch (Exception ignored){}

                callback.onResult(items, nextPage);
            }
        }).start();

    }
}
