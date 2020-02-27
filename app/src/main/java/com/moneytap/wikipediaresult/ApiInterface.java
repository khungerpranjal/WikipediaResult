package com.moneytap.wikipediaresult;

import com.moneytap.wikipediaresult.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/w/api.php")
    Call<BaseResponse> getSearchedData(@Query("action") String action,
                                       @Query("format") String format,
                                       @Query("prop") String prop,
                                       @Query("generator") String generator,
                                       @Query("formatversion") int formatversion,
                                       @Query("piprop") String piprop,
                                       @Query("pithumbsize") int thumbsize,
                                       @Query("pilimit") int pilimit,
                                       @Query("wbptterms") String terms,
                                       @Query("gpssearch") String searchedText,
                                       @Query("gpslimit") int gpslimit);
}
