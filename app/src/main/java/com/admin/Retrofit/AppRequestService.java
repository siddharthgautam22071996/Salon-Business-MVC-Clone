package com.admin.Retrofit;

import com.admin.Model.AllSaloonModel;
import com.admin.Model.AllServicesModel;
import com.admin.Model.Form1Model;
import com.admin.Model.Form2Model;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * * Interface through which all the api calls will be performed
 */
public interface AppRequestService {

    @POST("appadmin/index.php?tag=SalonSignUP")
    @FormUrlEncoded
    Observable<Form1Model> addForm1(@Field("business_Name") String business_Name,
                                     @Field("Contact_Person") String Contact_Person,
                                     @Field("contact_No") String contact_No,
                                     @Field("business_email_id") String business_email_id,
                                     @Field("Salon_password") String Salon_password);

    @POST("appadmin/index.php?tag=newsalon")
    @FormUrlEncoded
    Observable<Form2Model> addForm2(@Field("business_id") String business_id,
                                    @Field("No_of_Chairs") String No_of_Chairs,
                                    @Field("no_of_emp") String no_of_emp,
                                    @Field("opening_hours") String opening_hours,
                                    @Field("closing_hours") String closing_hours,
                                    @Field("latitude") String latitude,
                                    @Field("longitute") String longitute);

    @GET("appadmin/index.php?tag=allsalon")
    Observable<AllSaloonModel> getSaloonList();

    @GET("appadmin/index.php?tag=service")
    Observable<AllServicesModel> getServicesList();
}