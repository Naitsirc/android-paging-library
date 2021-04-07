package es.devtr.androidpaginglibrary;


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
                int cuantos = PAGE_SIZE;

                List<Item> items = new ArrayList<Item>();

                for(int i=0;i<cuantos;i++){

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
/*
        RetrofitClient.getInsance()
                .getApi()
                .getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        if(response.body() != null){

                            callback.onResult(response.body().items, null, FIRST_PAGE + 1);

                        }else{
                            List<Item> items = new ArrayList<Item>();

                            callback.onResult(items, null, FIRST_PAGE+1);
                        }

                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {

                    }
                });
*/
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

        android.util.Log.v("LoadBefore","=>"+params.key);


        new Thread(new Runnable() {
            @Override
            public void run() {

                int inicio = (params.key-1)*PAGE_SIZE;
                int cuantos = PAGE_SIZE;

                List<Item> items = new ArrayList<Item>();

                for(int i=0;i<cuantos;i++){

                    Item item = new Item();
                    item.index = ""+(i+inicio);

                    items.add(item);

                }

                try{
                    Thread.sleep(TIME);
                }catch (Exception ignored){}

                Integer key = (params.key > 1) ? params.key - 1 : null;

                callback.onResult(items, key);
            }
        }).start();

        /*
        RetrofitClient.getInsance()
                .getApi()
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {



                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().items, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        List<Item> items = new ArrayList<Item>();
                        Integer key = (params.key > 1) ? params.key - 1 : null;
                        callback.onResult(items, key);
                    }
                });*/

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

        android.util.Log.v("LoadAfter","=>"+params.key);

        new Thread(new Runnable() {
            @Override
            public void run() {

                int inicio = (params.key-1)*PAGE_SIZE;
                int cuantos = PAGE_SIZE;

                List<Item> items = new ArrayList<Item>();

                for(int i=0;i<cuantos;i++){

                    Item item = new Item();
                    item.index = ""+(i+inicio);

                    items.add(item);

                }

                try{
                    Thread.sleep(TIME);
                }catch (Exception ignored){}

                callback.onResult(items, params.key+1);
            }
        }).start();

        /*
        RetrofitClient.getInsance()
                .getApi()
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        if(response.body() != null){
                            Integer key = response.body().has_more ? params.key + 1 : null;
                            callback.onResult(response.body().items, key);
                        }

                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        List<Item> items = new ArrayList<Item>();
                        Integer key =  params.key + 1;
                        callback.onResult(items, key);
                    }
                });*/


    }
}
