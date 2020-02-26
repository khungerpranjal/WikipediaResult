package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.model.BaseResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(p0: View?) {

    }

    private var apiInterface: ApiInterface? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        rv_data_list.layoutManager = LinearLayoutManager(this)


        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(editTextSearch.text.length>0) {
                    rv_data_list.visibility=View.VISIBLE
                    getData(editTextSearch.text.toString())
                }

                else {
                    rv_data_list.visibility=View.INVISIBLE
                }
            }
        })
}

    fun getData(searchedText:String) {
        apiInterface!!.getSearchedData("query","json","pageimages|pageterms",
            "prefixsearch",2,"thumbnail",50,10,
            "description",searchedText,10).enqueue(object : retrofit2.Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {

                Toast.makeText(this@MainActivity,"Please check your internet connection",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.isSuccessful) {
                    if(response.body()!=null && response.body()?.query!=null) {
                        rv_data_list?.adapter=ListAdapter(response.body()?.query!!.pages,this@MainActivity)
                    }
                    else {
                        rv_data_list.adapter=null
                    }
                }
            }
        })
    }

}

