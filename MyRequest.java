package com.example.lebeaubafouidizo.rapidews;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.antapp.flash_ws.config.GetObjectList;
import com.antapp.flash_ws.interfaces.AntappNetWorkHelper;
import com.antapp.flash_ws.interfaces.AntappObjectListListener;
import com.antapp.flash_ws.utils.AntappNetworkCacheHelper;
import com.antapp.flash_ws.utils.AntappUtilsMethods;
import com.antapp.flash_ws.utils.HandleError;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lebeau BAFOUIDIZO on 12/02/2018.
 */

public class MyRequest implements AntappNetWorkHelper {

    private Context context;
    private String url;
    private Object progressObject;

    public MyRequest(Context context, String url, Object progressObject) {
        this.context = context;
        this.url = url;
        this.progressObject = progressObject;
    }


    @Override
    public void onNetworkRequest() {

            GetObjectList listWithTag = new GetObjectList(context,url,progressObject);

            listWithTag.getObjectListFromServer("voiture_liste",Voiture.class, new AntappObjectListListener() {
                @Override
                public void ifNoNetwork(RequestQueue queue,StringRequest request,JSONArray jsonArray,List<?> myObjectlist) {
                    if (!AntappUtilsMethods.isNetworkAvailable(context)){
                        //si ya pas internet on selectionne dans le cache les element
                            //annullation de la requette
                            AntappUtilsMethods.cancelRequest(queue,request);
                            List<Voiture> voitureList = (List<Voiture>) myObjectlist;
//                            JSONArray jsonArray = AntappNetworkCacheHelper.getCacheEntryByUrl(context,url,"voiture_liste");

                            Toast.makeText(context, voitureList.size()+" no connection", Toast.LENGTH_SHORT).show();


                    }
                }

                @Override
                public void onListResponse(JSONArray jsonArray, List<?> myObjectlist) {

                    Log.e("arra",jsonArray.toString());
                    String serie,marque,km;

                    List<Voiture> voitureList = (List<Voiture>) myObjectlist;

                    for (int i = 0;i<voitureList.size();i++){
                        Toast.makeText(context, voitureList.get(i).getSerie()+"\n---"+
                                voitureList.get(i).getMarque()+"\n---"+
                                voitureList.get(i).getKm(), Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onError(VolleyError myError) {
                    //affiche les erreurs
                    HandleError.displayNetWorkError(myError,context);
                }

            });

    }


}
