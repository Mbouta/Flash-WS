package com.example.lebeaubafouidizo.rapidews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.antapp.flash_ws.config.PostStringAndGetObjectList;
import com.antapp.flash_ws.interfaces.AntappTagObjectListListener;
import com.antapp.flash_ws.utils.AntappUtilsMethods;
import com.antapp.flash_ws.utils.HandleError;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    String serie,marque,km;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = findViewById(R.id.progress);
//        imageView = (ImageView) findViewById(R.id.imageView);

        recyclerView = (RecyclerView) findViewById(R.id.recycl);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*AntappUtilsMethods.quickImageUrlIntoGlide(
                MainActivity.this,
                "http://i.imgur.com/DvpvklR.png",
                imageView
        );*/


        String url = "http://www.antapp-inspiration.com/hack4work/ws/SimpleString.php";
        String urlUpdate = "http://www.antapp-inspiration.com/hack4work/ws/SimpleUpdateString.php";
        String urlListObject = "http://www.antapp-inspiration.com/hack4work/ws/SimpleListObject.php";
        String urlListObjectTAG = "http://www.antapp-inspiration.com/hack4work/ws/SimpleTagListObject.php";
        String urlDelete = "http://www.antapp-inspiration.com/hack4work/ws/SimpleDelete.php";

        PostStringAndGetObjectList objectList = new PostStringAndGetObjectList(MainActivity.this,urlListObjectTAG,progressBar);

        Map<String,String> map = new HashMap<>();
        map.put("Km","100");
        map.put("kone","vente");

        objectList.getObjectListWithTag(map,"voiture_liste", Voiture.class, new AntappTagObjectListListener() {
            @Override
            public void ifNoNetwork(RequestQueue queue, StringRequest request, JSONArray jsonArray, List<?> myObjectlist) {
                if (!AntappUtilsMethods.isNetworkAvailable(MainActivity.this)){

                    List<Voiture> voitureList = (List<Voiture>) myObjectlist;
                    adapter = new AdapterVoiture(MainActivity.this,voitureList);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, voitureList.size()+"", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onListResponse(JSONArray jsonArray, List<?> myObjectlist) {

                List<Voiture> voitureList = (List<Voiture>) myObjectlist;
                adapter = new AdapterVoiture(MainActivity.this,voitureList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(VolleyError myError) {
                HandleError.displayNetWorkError(myError,MainActivity.this);
            }
        });

//        new MyRequest(MainActivity.this,urlListObject,progressBar).onNetworkRequest();


        /*PostObjectAndGetString response = new PostObjectAndGetString(MainActivity.this,url,false,progressBar);
//        response.insertObjectAndGetString(new Voiture("53","vols","52"),null);

        response.insertObjectAndGetString(
                new Voiture("53", "vols", "52"),new AntappStringListener() {

                    @Override
                    public void onStringResponse(String myResponse) {
                        Toast.makeText(MainActivity.this, myResponse, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(MainActivity.this, error+"", Toast.LENGTH_SHORT).show();
                    }
                });*/



        /*final GetObjectList response = new GetObjectList(MainActivity.this,urlListObject,progressBar);
        response.getObjectListFromServer( "voiture_liste",
                new AntappObjectListListener() {
                    @Override
                    public void ifNoNetwork(RequestQueue queue, StringRequest request) {

                    }

                    @Override
                    public void onListResponse(JSONArray jsonArray) {

                        List<Voiture> voitureList = new ArrayList<>();
                        for (int i = 0; i<jsonArray.length();i++){
                            try {
                                JSONObject jobect = jsonArray.getJSONObject(i);
                                serie = jobect.getString("Serie");
                                marque = jobect.getString("Marque");
                                km = jobect.getString("Km");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Voiture voiture = new Voiture(serie,marque,km);
                            voitureList.add(voiture);

                            adapter = new AdapterVoiture(getApplicationContext(),voitureList);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onError(VolleyError myError) {

                    }
                });*/

        //tag and get object List
       /* PostStringAndGetObjectList response = new PostStringAndGetObjectList(MainActivity.this,urlListObjectTAG,"voiture_liste",false,progressBar);
        Map<String,String> tag = new HashMap<>();
        tag.put("Km","100");

        response.getObjectListWithTag(tag,
                new AntappTagObjectListListener() {
                    @Override
                    public void onListResponse(JSONArray jsonArray) {

                        List<Voiture> voitureList = new ArrayList<>();
                        for (int i = 0; i<jsonArray.length();i++){
                            try {
                                JSONObject jobect = jsonArray.getJSONObject(i);

//                                voitureList.add(new Voiture(jobect));
                                serie = jobect.getString("Serie");
                                marque = jobect.getString("Marque");
                                km = jobect.getString("Km");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Voiture voiture = new Voiture(serie,marque,km);
                            voitureList.add(voiture);

                            adapter = new AdapterVoiture(getApplicationContext(),voitureList);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onError(VolleyError myError) {

                    }
                });*/




        /*JSONArray jsonArray = AntappNetworkCacheHelper.getCacheEntryByUrl(MainActivity.this,urlListObjectTAG,"voiture_liste");
        if (jsonArray==null){
            Log.e("cache","null");
        }else {
            Log.e("cache","pa null");
        }*/





        //cache



        //delete object
        /*DeleteObjectWithTag response = new DeleteObjectWithTag(MainActivity.this,urlDelete,false,progressBar);
        Map<String,String> tag = new HashMap<>();
        tag.put("Km","100");

        response.deleteObject(tag,
                new AntappDeleteListener() {
                    @Override
                    public void onDeleteResponse(String status) {
                        Toast.makeText(MainActivity.this, status+"", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(VolleyError myError) {

                    }
                });*/


        //update object
        /*UpdateObjectWithTag response = new UpdateObjectWithTag(MainActivity.this,urlUpdate,false,progressBar);

        Map<String,String> tag = new HashMap<>();
        tag.put("tag_km","100");
        tag.put("tag_Serie","vols wagen");
        response.updateObject(new Voiture("1", "levos", "30"), tag,
                new AntappUpdateListener() {
                    @Override
                    public void onUpdateResponse(String status) {
                        Toast.makeText(MainActivity.this, status+"", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(VolleyError myError) {

                    }
                });*/

    }

   /* public JSONObject getVolleyCacheEntryByUrl(Context c, String url) {
        // RequestQueue queue = Volley.newRequestQueue(c);
        String cachedResponse = new String(VolleySingleton
                .getInstance(c)
                .getRequestQueue()
                .getCache()
                .get(url).data);

        try {
            JSONObject cacheObj = new JSONObject(cachedResponse);
            Log.e("CacheResult", cacheObj.toString());
            return cacheObj;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }*/



}
