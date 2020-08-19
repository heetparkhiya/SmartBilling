package com.example.smartbilling.API;

import com.example.smartbilling.Bean.Bean_Response_Broker;
import com.example.smartbilling.Bean.Bean_Response_Collection;
import com.example.smartbilling.Bean.Bean_Response_Company;
import com.example.smartbilling.Bean.Bean_Response_CreditNote;
import com.example.smartbilling.Bean.Bean_Response_General;
import com.example.smartbilling.Bean.Bean_Response_ListEntry;
import com.example.smartbilling.Bean.Bean_Response_Party;
import com.example.smartbilling.Bean.Bean_Response_Product;
import com.example.smartbilling.Bean.Bean_Response_Product_Item_Size;
import com.example.smartbilling.Bean.Bean_Response_Product_Multi;
import com.example.smartbilling.Bean.Bean_Response_Size;
import com.example.smartbilling.Bean.Bean_Response_Transport;
import com.example.smartbilling.Bean.Bean_Response_User;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("User/login.php")
    Call<Bean_Response_User> LoginUser(@Query("Username") String UserName, @Query("Password") String UserPassword);

    @GET("Transport/SelectAll.php")
    Call<Bean_Response_Transport> getAllTransport(@Query("UserID") String UserID);

    @FormUrlEncoded
    @POST("Transport/Insert.php")
    Call<Bean_Response_Transport> InsertTransport(
            @Field("UserID") String UserID,
            @Field("TransportName") String TransportName,
            @Field("TransportAddress") String TransportAddress,
            @Field("TransportMobileNumber") String TransportMobileNumber,
            @Field("Remarks") String Remarks);

    @FormUrlEncoded
    @POST("Transport/Update.php")
    Call<Bean_Response_Transport> UpdateTransport(
            @Field("UserID") String UserID,
            @Field("TransportID") String TransportID,
            @Field("TransportName") String TransportName,
            @Field("TransportAddress") String TransportAddress,
            @Field("TransportMobileNumber") String TransportMobileNumber,
            @Field("Remarks") String Remarks);

    @DELETE("Transport/Delete.php")
    Call<Bean_Response_Transport> DeleteTransport(@Query("UserID") String UserID, @Query("TransportID") String TransportID);

    @GET("Size/SelectAll.php")
    Call<Bean_Response_Size> getAllSize(@Query("UserID") String UserID);

    @FormUrlEncoded
    @POST("Size/Insert.php")
    Call<Bean_Response_Size> InsertSize(
            @Field("UserID") String UserID,
            @Field("Size") String Size,
            @Field("Remarks") String Remarks);

    @FormUrlEncoded
    @POST("Size/Update.php")
    Call<Bean_Response_Size> UpdateSize(
            @Field("UserID") String UserID,
            @Field("SizeID") String SizeID,
            @Field("Size") String Size,
            @Field("Remarks") String Remarks);

    @DELETE("Size/Delete.php")
    Call<Bean_Response_Size> DeleteSize(@Query("UserID") String UserID, @Query("SizeID") String SizeID);

    @GET("Company/SelectAll.php")
    Call<Bean_Response_Company> getAllCompany(@Query("UserID") String UserID);

    @FormUrlEncoded
    @POST("Company/Insert.php")
    Call<Bean_Response_Company> InsertCompany(
            @Field("UserID") String UserID,
            @Field("CompanyName") String CompanyName,
            @Field("CompanyAddress") String CompanyAddress,
            @Field("CompanyContactNumber") String CompanyContactNumber,
            @Field("CompanyFaxNumber") String CompanyFaxNumber,
            @Field("CompanyEmail") String CompanyEmail,
            @Field("CompanyVAT_TIN_No") String CompanyVAT_TIN_No,
            @Field("CompanyCST_TIN_No") String CompanyCST_TIN_No,
            @Field("Remarks") String Remarks);

    @FormUrlEncoded
    @POST("Company/Update.php")
    Call<Bean_Response_Company> UpdateCompany(
            @Field("UserID") String UserID,
            @Field("CompanyID") String CompanyID,
            @Field("CompanyName") String CompanyName,
            @Field("CompanyAddress") String CompanyAddress,
            @Field("CompanyContactNumber") String CompanyContactNumber,
            @Field("CompanyFaxNumber") String CompanyFaxNumber,
            @Field("CompanyEmail") String CompanyEmail,
            @Field("CompanyVAT_TIN_No") String CompanyVAT_TIN_No,
            @Field("CompanyCST_TIN_No") String CompanyCST_TIN_No,
            @Field("Remarks") String Remarks);

    @DELETE("Company/Delete.php")
    Call<Bean_Response_Company> DeleteCompany(@Query("UserID") String UserID, @Query("CompanyID") String CompanyID);

    @GET("Broker/SelectAll.php")
    Call<Bean_Response_Broker> getAllBroker(@Query("UserID") String UserID);

    @FormUrlEncoded
    @POST("Broker/Insert.php")
    Call<Bean_Response_Broker> InsertBroker(
            @Field("UserID") String UserID,
            @Field("BrokerName") String BrokerName,
            @Field("BrokerAddress") String BrokerAddress,
            @Field("BrokerTelephoneNumber") String BrokerTelephoneNumber,
            @Field("BrokerFaxNumber") String BrokerFaxNumber,
            @Field("BrokerMobileNumber") String BrokerMobileNumber,
            @Field("BrokerEmail") String BrokerEmail,
            @Field("BrokerRate") String BrokerRate,
            @Field("Remarks") String Remarks);

    @FormUrlEncoded
    @POST("Broker/Update.php")
    Call<Bean_Response_Broker> UpdateBroker(
            @Field("UserID") String UserID,
            @Field("BrokerID") String BrokerID,
            @Field("BrokerName") String BrokerName,
            @Field("BrokerAddress") String BrokerAddress,
            @Field("BrokerTelephoneNumber") String BrokerTelephoneNumber,
            @Field("BrokerFaxNumber") String BrokerFaxNumber,
            @Field("BrokerMobileNumber") String BrokerMobileNumber,
            @Field("BrokerEmail") String BrokerEmail,
            @Field("BrokerRate") String BrokerRate,
            @Field("Remarks") String Remarks);

    @DELETE("Broker/Delete.php")
    Call<Bean_Response_Broker> DeleteBroker(@Query("UserID") String UserID, @Query("BrokerID") String BrokerID);

    @GET("Party/SelectAll.php")
    Call<Bean_Response_Party> getAllParty(@Query("UserID") String UserID);

    @FormUrlEncoded
    @POST("Party/Insert.php")
    Call<Bean_Response_Party> InsertParty(
            @Field("UserID") String UserID,
            @Field("TransportID") String TransportID,
            @Field("BrokerID") String BrokerID,
            @Field("Name") String Name,
            @Field("ReferenceName") String ReferenceName,
            @Field("Address") String Address,
            @Field("BankThrough") String BankThrough,
            @Field("PIN") String PIN,
            @Field("City") String City,
            @Field("State") String State,
            @Field("TelephoneNumber") String TelephoneNumber,
            @Field("FaxNumber") String FaxNumber,
            @Field("MobileNumber") String MobileNumber,
            @Field("EmailID") String EmailID,
            @Field("PartyLocation") String PartyLocation,
            @Field("CreditDays") String CreditDays,
            @Field("TINNo") String TINNo,
            @Field("CSTNo") String CSTNo,
            @Field("DISC") String DISC,
            @Field("BrokerRage") String BrokerRage,
            @Field("Remarks") String Remarks
    );

    @FormUrlEncoded
    @POST("Party/Update.php")
    Call<Bean_Response_Party> UpdateParty(
            @Field("UserID") String UserI,
            @Field("PartyID") String PartyID,
            @Field("TransportID") String TransportID,
            @Field("BrokerID") String BrokerID,
            @Field("Name") String Name,
            @Field("ReferenceName") String ReferenceName,
            @Field("Address") String Address,
            @Field("BankThrough") String BankThrough,
            @Field("PIN") String PIN,
            @Field("City") String City,
            @Field("State") String State,
            @Field("TelephoneNumber") String TelephoneNumber,
            @Field("FaxNumber") String FaxNumber,
            @Field("MobileNumber") String MobileNumber,
            @Field("EmailID") String EmailID,
            @Field("PartyLocation") String PartyLocation,
            @Field("CreditDays") String CreditDays,
            @Field("TINNo") String TINNo,
            @Field("CSTNo") String CSTNo,
            @Field("DISC") String DISC,
            @Field("BrokerRage") String BrokerRage,
            @Field("Remarks") String Remarks
    );

    @DELETE("Party/Delete.php")
    Call<Bean_Response_Party> DeleteParty(@Query("UserID") String UserID, @Query("PartyID") String PartyID);

    @GET("List/SelectAll.php")
    Call<Bean_Response_ListEntry> getAllListEntry(@Query("UserID") String UserID, @Query("ListType") String ListType);

    @DELETE("List/Delete.php")
    Call<Bean_Response_ListEntry> DeleteListEntry(@Query("UserID") String UserID, @Query("ListID") String ListID);

    @FormUrlEncoded
    @POST("List/Insert.php")
    Call<Bean_Response_ListEntry> InsertListEntry(
            @Field("ListType") String ListType,
            @Field("UserID") String UserID,
            @Field("PartyID") String PartyID,
            @Field("ProductID") String ProductID,
            @Field("ListNo") String ListNo,
            @Field("ListDate") String ListDate,
            @Field("NoofCases") String NoofCases,
            @Field("TotalQuantity") String TotalQuantity,
            @Field("Remarks") String Remarks);

    @FormUrlEncoded
    @POST("List/Update.php")
    Call<Bean_Response_ListEntry> UpdateListEntry(
            @Field("UserID") String UserID,
            @Field("ListID") String ListID,
            @Field("PartyID") String PartyID,
            @Field("ProductID") String ProductID,
            @Field("ListNo") String ListNo,
            @Field("ListDate") String ListDate,
            @Field("NoofCases") String NoofCases,
            @Field("TotalQuantity") String TotalQuantity,
            @Field("Remarks") String Remarks);

    @DELETE("Product/Delete.php")
    Call<Bean_Response_General> DeleteProduct(@Query("UserID") String UserID,@Query("ProductID") String productID);

    @FormUrlEncoded
    @POST("Product/Insert.php")
    Call<Bean_Response_Product_Multi> InsertProduct(@Field("UserID") String UserID,
                                                    @Field("ProductDesignNumber") String ProductDesignNumber,
                                                    @Field("ProductName") String ProductName,
                                                    @Field("ProductMRP_PR") String ProductMRP_PR,
                                                    @Field("ProductStyle") String ProductStyle,
                                                    @Field("Remarks") String Remarks);

    @GET("Product/SelectAll.php")
    Call<Bean_Response_Product> getAllProduct(@Query("UserID") String UserID);

    @FormUrlEncoded
    @POST("Product/Update.php")
    Call<Bean_Response_General> UpdateProduct(
            @Field("UserID") String UserID,
            @Field("ProductID") String ProductID,
            @Field("ProductDesignNumber") String ProductDesignNumber,
            @Field("ProductName") String ProductName,
            @Field("ProductMRP_PR") String ProductMRP_PR,
            @Field("ProductStyle") String ProductStyle,
            @Field("Remarks") String Remarks,
            @Field("Product") String Product);

    @FormUrlEncoded
    @POST("Product/InsertProductWiseSize.php")
    Call<Bean_Response_General> InsertProductWiseSize(@Field("Product") String Product);

    @GET("CreditNote/SelectAll.php")
    Call<Bean_Response_CreditNote> getAllCreditNote(@Query("UserID") String UserID);

    @DELETE("CreditNote/Delete.php")
    Call<Bean_Response_CreditNote> DeleteCreditNote(@Query("UserID") String UserID, @Query("CreditNoteID") String CreditNoteID);

    @FormUrlEncoded
    @POST("CreditNote/Insert.php")
    Call<Bean_Response_CreditNote> InsertCreditNote(
            @Field("UserID") String UserID,
            @Field("PartyID") String PartyID,
            @Field("CreditNoteNo") String CreditNoteNo,
            @Field("CreditDate") String CreditDate,
            @Field("NoofCases") String NoofCases,
            @Field("InvoiceNo") String InvoiceNo,
            @Field("InvoiceDate") String InvoiceDate,
            @Field("TotalQuantity") String TotalQuantity,
            @Field("TotalAmount") String TotalAmount,
            @Field("DiscountPR") String DiscountPR,
            @Field("Discount") String Discount,
            @Field("Total") String Total,
            @Field("TaxPR") String TaxPR,
            @Field("Tax") String Tax,
            @Field("OtherCharges") String OtherCharges,
            @Field("GrandTotal") String GrandTotal,
            @Field("CForm") String CForm,
            @Field("Remarks") String Remarks);

    @FormUrlEncoded
    @POST("CreditNote/Update.php")
    Call<Bean_Response_CreditNote> UpdateCreditNote(
            @Field("UserID") String UserID,
            @Field("CreditNoteID") String CreditNoteID,
            @Field("PartyID") String PartyID,
            @Field("CreditNoteNo") String CreditNoteNo,
            @Field("CreditDate") String CreditDate,
            @Field("NoofCases") String NoofCases,
            @Field("InvoiceNo") String InvoiceNo,
            @Field("InvoiceDate") String InvoiceDate,
            @Field("TotalQuantity") String TotalQuantity,
            @Field("TotalAmount") String TotalAmount,
            @Field("DiscountPR") String DiscountPR,
            @Field("Discount") String Discount,
            @Field("Total") String Total,
            @Field("TaxPR") String TaxPR,
            @Field("Tax") String Tax,
            @Field("OtherCharges") String OtherCharges,
            @Field("GrandTotal") String GrandTotal,
            @Field("CForm") String CForm,
            @Field("Remarks") String Remarks);

    @GET("Collection/SelectAll.php")
    Call<Bean_Response_Collection> getAllCollection(@Query("UserID") String UserID);

    @DELETE("Collection/Delete.php")
    Call<Bean_Response_Collection> DeleteCollection(@Query("UserID") String UserID, @Query("CollectionID") String CollectionID);

    @FormUrlEncoded
    @POST("Collection/Insert.php")
    Call<Bean_Response_Collection> InsertCollection(
            @Field("UserID") String UserID,
            @Field("PartyID") String PartyID,
            @Field("BrokerID") String BrokerID,
            @Field("CreditNoteID") String CreditNoteID,
            @Field("BillNo") String BillNo,
            @Field("BillDate") String BillDate,
            @Field("Days") String Days,
            @Field("BankName") String BankName,
            @Field("BankBranch") String BankBranch,
            @Field("CollectionNo") String CollectionNo,
            @Field("CollectionDate") String CollectionDate,
            @Field("ModeofPayment") String ModeofPayment,
            @Field("ChequeNo") String ChequeNo,
            @Field("ChequeDate") String ChequeDate,
            @Field("CollDate") String CollDate,
            @Field("BillAmount") String BillAmount,
            @Field("DiscountPR") String DiscountPR,
            @Field("Discount") String Discount,
            @Field("ReceivedAmount") String ReceivedAmount,
            @Field("CollectionAmount") String CollectionAmount,
            @Field("ClearenceDate") String ClearanceDate,
            @Field("Remarks") String Remarks);

    @FormUrlEncoded
    @POST("Collection/Update.php")
    Call<Bean_Response_Collection> UpdateCollection(
            @Field("UserID") String UserID,
            @Field("CollectionID") String CollectionID,
            @Field("PartyID") String PartyID,
            @Field("BrokerID") String BrokerID,
            @Field("CreditNoteID") String CreditNoteID,
            @Field("BillNo") String BillNo,
            @Field("BillDate") String BillDate,
            @Field("Days") String Days,
            @Field("BankName") String BankName,
            @Field("BankBranch") String BankBranch,
            @Field("CollectionNo") String CollectionNo,
            @Field("CollectionDate") String CollectionDate,
            @Field("ModeofPayment") String ModeofPayment,
            @Field("ChequeNo") String ChequeNo,
            @Field("ChequeDate") String ChequeDate,
            @Field("CollDate") String CollDate,
            @Field("BillAmount") String BillAmount,
            @Field("DiscountPR") String DiscountPR,
            @Field("Discount") String Discount,
            @Field("ReceivedAmount") String ReceivedAmount,
            @Field("CollectionAmount") String CollectionAmount,
            @Field("ClearenceDate") String ClearanceDate,
            @Field("Remarks") String Remarks);

    @GET("Product/SelectSizeByProductID.php")
    Call<Bean_Response_Product_Item_Size> SelectSizeByProductID(@Query("ProductID") String ProductID);
}
