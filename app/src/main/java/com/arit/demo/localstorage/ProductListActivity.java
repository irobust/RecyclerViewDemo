package com.arit.demo.localstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arit.demo.localstorage.model.Product;
import com.arit.demo.localstorage.services.ProductService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductListActivity extends AppCompatActivity {
    @BindView(R.id.rcvProduct)
    RecyclerView rcvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        ButterKnife.bind(this);

        Retrofit retrofit = ProductService.retrofit;
        ProductService service = retrofit.create(ProductService.class);

//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        Call<List<Product>> products = service.getAll();
        products.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                rcvProduct.setLayoutManager(layoutManager);
                ProductAdapter adapter = new ProductAdapter(response.body());
                rcvProduct.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("DEBUG", "Fail to connect api");
            }
        });
    }
}
