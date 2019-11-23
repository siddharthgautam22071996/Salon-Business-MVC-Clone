package com.abhi.fabkutbusiness.retrofit;


import com.abhi.fabkutbusiness.accounting.model.ResponseAccountReport;
import com.abhi.fabkutbusiness.accounting.model.ResponseGenerateVoucherNo;
import com.abhi.fabkutbusiness.accounting.model.ResponseGetVoucherDetails;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseLedgerSelect;
import com.abhi.fabkutbusiness.accounting.model.ResponsePoInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseSelectPo;
import com.abhi.fabkutbusiness.accounting.model.ResponseSelectPo2;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodayInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseTodaysStatement;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherInsert;
import com.abhi.fabkutbusiness.accounting.model.ResponseVoucherUpdate;
import com.abhi.fabkutbusiness.billing.model.ResponsePomoCode;
import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmHistory;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmList;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfo;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseUnpaidAmount;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpAttendence;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpCheckIn;
import com.abhi.fabkutbusiness.employee.model.ResponseEmpReport;
import com.abhi.fabkutbusiness.employee.model.ResponseModelEmp;
import com.abhi.fabkutbusiness.employee.model.ResponseModelEmpSummary;
import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseItemBrand;
import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseItemCategory;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieve;
import com.abhi.fabkutbusiness.inventory.order.model.ResponseOrderRecieveData;
import com.abhi.fabkutbusiness.inventory.vendor.model.ResponseCity;
import com.abhi.fabkutbusiness.inventory.vendor.model.ResponseLocation;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelLogin;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.marketing.model.ResponseCreatePromotion;
import com.abhi.fabkutbusiness.marketing.model.ResponseLatestOfferList;
import com.abhi.fabkutbusiness.marketing.model.ResponseUpComingBirthday;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * * Interface through which all the api calls will be performed
 */
public interface AppRequestService {


    @POST("salon/booking/index.php?tag=salonlogin")
    Observable<ResponseModelLogin> loginApiMethod(@Query("email") String user, @Query("password") String pass);


    @POST("salon/booking/index.php?tag=customer")
    Observable<ResponseModelCustomer> customerApiMethod(@Query("business_id") String business_id);

    @POST("salon/booking/index.php?tag=salonemp")
    Observable<ResponseModelEmployee> employeeApiMethod(@Query("business_id") String business_id);

    @POST("salon/booking/index.php?tag=rateinfo")
    Observable<ResponseModelRateInfo> rateInfoApiMethod(@Query("business_id") String business_id);


    @POST("crm/index.php?tag=cstList")
    Observable<ResponseCrmList> CrmListShowApiMethod(@Query("business_id") int business_id);

    @POST("crm/index.php?tag=cstbasic")
    Observable<ResponseBasicInfo> CrmBasicInfoShowApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") String Enduser_id);

    @POST("crm/index.php?tag=cstsocial")
    Observable<ResponseSocialInfo> CrmSocialInfoShowApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") String Enduser_id);

    @POST("crm/index.php?tag=cstpersonal")
    Observable<ResponsePersonalInfo> CrmPersonalInfoShowApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") String Enduser_id);

    @POST("crm/index.php?tag=cstbasicUP")
    Observable<ResponseBasicInfoUpdate> CrmBasicInfoUpdateApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") String EndUser_id, @Query("enduser_name") String enduser_name, @Query("lname") String lname, @Query("gender") String gender, @Query("email") String email, @Query("contact_no") String contact_no, @Query("alternetContact") String alternetContact, @Query("allergies") String allergies, @Query("Profile_Comp_Basic") int Profile_Comp_Basic);

    @POST("crm/index.php?tag=cstPerUP")
    Observable<ResponsePersonalInfoUpdate> CrmPersonalInfoUpdateApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") String EndUser_id, @Query("dob") String dob, @Query("anidate") String anidate, @Query("m_um") int m_um, @Query("Profile_Comp_Personal") int Profile_Comp_Personal);

    @POST("crm/index.php?tag=cstsocialUP")
    Observable<ResponseSocialInfoUpdate> CrmSocialInfoUpdateApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") String EndUser_id, @Query("Social_Home_address") String Social_Home_address, @Query("Social_Delivery_Address") String Social_Delivery_Address, @Query("Social_Mode_Commincation") String Social_Mode_Commincation, @Query("Social_FB_ID") String Social_FB_ID, @Query("Social_Twitter_ID") String Social_Twitter_ID, @Query("Social_whatsApp") String Social_whatsApp, @Query("Profile_Comp_Social") int Profile_Comp_Social);

    @POST("booking/index.php?tag=syncuser1")
    Observable<ResponseModel> addCustomerApiMethod(@Query("ENDUSERCODE") String ENDUSERCODE,
                                                   @Query("business_id") String business_id,
                                                   @Query("enduser_name") String endUser_firstName,
                                                   @Query("gender") String gender,
                                                   @Query("email") String email,
                                                   @Query("contact_no") String contact_no,
                                                   @Query("allergies") String allergies,
                                                   @Query("dob") String dob);

    @POST("booking/index.php?tag=synBooking")
    Observable<ResponseModel1> bookingApiMethod(@Query("BookingCode") String BookingCode,
                                               @Query("EnduserID") String EnduserID,
                                               @Query("Salon_ID") String SalonId,
                                               @Query("TimeSlot") String TimeSlot,
                                               @Query("Empid") String Empid,
                                               @Query("Services") String Services,
                                               @Query("MobileNo") String MobileNo,
                                               @Query("Remark") String Remark,
                                               @Query("PrevBooking") String PrevBooking,
                                               @Query("BookingDate") String BookingDate,
                                               @Query("SeatNo") String SeatNo,
                                               @Query("Assign_book") String Assign_book);


    @POST("account/index.php?tag=LedgerSelect")
    Observable<ResponseLedgerSelect> LedgerSelectApiMethod(@Query("business_id") String business_id);

    @POST("account/index.php?tag=LedgerInsert")
    Observable<ResponseLedgerInsert>LedgerInsertApiMethod(@Query("business_id") int business_id,
                                                          @Query("ledger_balance") String ledger_balance);

    @POST("account/index.php?tag=SelectPO")
    Observable<ResponseSelectPo> SelectPoApiMethod(@Query("business_id") int business_id);

    @POST("account/index.php?tag=GenerateVoucherNo")
    Observable<ResponseGenerateVoucherNo> GenerateVoucherNoApiMethod(@Query("business_id") int business_id);

    @POST("account/index.php?tag=SelectPO")
    Observable<ResponseSelectPo2> selectPoApiMethod(@Query("business_id") int business_id,
                                                    @Query("PONO") int PONO);

    @POST("account/index.php?tag=VOCInsert")
    Observable<ResponseVoucherInsert> VoucherInsertApiMethod(@Query("business_id") int business_id,
                                                             @Query("VoucherNo") String VoucherNo,
                                                             @Query("money") int money , @Query("assignTo") String assignTo,
                                                             @Query("emp_id") String empId, @Query("remark") String remark);

    @POST("account/index.php?tag=GetTodaysStatement")
    Observable<ResponseTodaysStatement> GetTodaysStatementApiMethod(@Query("business_id") String business_id);

    @POST("account/index.php?tag=Expense_PO")
    Observable<ResponsePoInsert>PoInsertApiMethod(@Query("business_id") int business_id,
                                                  @Query("PaidAmount") String PaidAmount,
                                                  @Query("PONO") int PONO, @Query("mode") String mode,
                                                  @Query("status") String status,
                                                  @Query("AutoID") String AutoID, @Query("TotalAmount")  int TotalAmount,
                                                  @Query("AmountBalnce") int AmountBalnce);

    @POST("account/index.php?tag=TodaysStatementCreate")
    Observable<ResponseTodayInsert>TodaysStatementInsertApiMethod(@Query("business_id") String business_id,
                                                                  @Query("FinalAmount") int FinalAmount,
                                                                  @Query("BookingAmount") int BookingAmount,
                                                                  @Query("AdvanceAmount") int AdvanceAmount,
                                                                  @Query("VoucherAmount") int VoucherAmount,
                                                                  @Query("PoAmount") int PoAmount,
                                                                  @Query("ledgerBalance") int ledgerBalance,
                                                                  @Query("CloseAmount") int CloseAmount ,
                                                                  @Query("DrawAmount") String DrawAmount);

    @POST("account/index.php?tag=GetVoucherDetailsnew")
    Observable <ResponseGetVoucherDetails>getVoucherDetailsApiMethod(@Query("business_id") String business_id);

    @POST("account/index.php?tag=VOCUPDATE")
    Observable <ResponseVoucherUpdate>VoucherUpdateApiMethod(@Query("business_id") int business_id,
                                                             @Query("VoucherNo") String VoucherNo,
                                                             @Query("money") String money ,
                                                             @Query("assignTo") String assignTo,
                                                             @Query("emp_id") String empId,
                                                             @Query("remark") String remark);

    @POST("admin/index.php?tag=city")
    Observable<ResponseCity> cityApiMethod();

    @POST("admin/index.php?tag=location")
    Observable<ResponseLocation> locationApiMethod(@Query("city_id") int city_id);

    @POST("crm/index.php?tag=csthistry1")
    Observable <ResponseCrmHistory>crmHistoryApi(@Query("business_id") String business_id, @Query("Enduser_id") String Enduser_id);

    @POST("inv/index.php?tag=getProductList")
    Observable <ResponseOrderRecieve>itemApi(@Query("business_id") String business_id);

    @POST("inv/index.php?tag=brand")
    Observable <ResponseItemBrand> brandtApiMethod();

    @POST("inv/index.php?tag=itemcat")
    Observable<ResponseItemCategory> itemCategoryApiMethod();

    @POST("emp/index.php?tag=empatt")
    Observable<ResponseEmpAttendence> employeCheckInApiMethod(@Query("business_id") String business_id, @Query("EmpID") String empID,
                                                              @Query("InTime") String inTime, @Query("OutTime") String outTime,
                                                              @Query("AttDate") String attDate, @Query("Remark") String remark);

    @POST("emp/index.php?tag=empattUpdate")
    Observable <ResponseEmpAttendence>employeCheckOutApiMethod(@Query("AttID") String attID, @Query("business_id") String business_id,
                                                               @Query("EmpID") String empID,@Query("InTime") String inTime,
                                                               @Query("OutTime") String outTime,@Query("AttDate") String attDate,
                                                               @Query("Remark") String remark);

    @POST("emp/index.php?tag=empRep")
    Observable <ResponseModelEmp>employeSummaryApiMethod(@Query("business_id") String business_id, @Query("Date") String date);

    @POST("emp/index.php?tag=empcheckin")
    Observable<ResponseEmpCheckIn> GetEmployeCheckInApiMethod(@Query("business_id") String business_id);

    @POST("emp/index.php?tag=empcheckOut")
    Observable<ResponseEmpCheckIn> GetEmployeCheckOutApiMethod(@Query("business_id") String business_id);

    @POST("emp/index.php?tag=empLeaveReport")
    Observable<ResponseEmpReport>EmployeeLeaveReportApiMethod(@Query("SalonID") String SalonID);

    @POST("emp/index.php?tag=UnpaidAmount")
    Observable<ResponseUnpaidAmount> CustomerUnpaidApi(@Query("CustomerID") String customerID);

    @POST("emp/index.php?tag=empLeave")
    Observable <ResponseModel1>employeeLeaveApi(@Query("business_id") String business_id,@Query("Emp_id") String emp_id,
                                                @Query("from_date") String from_date,@Query("to_date") String to_date,
                                                @Query("comment") String comment, @Query("halfLeave") String halfLeave,
                                                @Query("Leavetotal") String leavetotal);

    @POST("emp/index.php?tag=empLeaveUpdate")
    Observable <ResponseModel1> employeeLeaveUdateApi(@Query("business_id") String business_id,@Query("Emp_id") String emp_id,
                                                      @Query("from_date") String from_date,@Query("to_date") String to_date,
                                                      @Query("comment") String comment, @Query("halfLeave") String halfLeave,
                                                      @Query("Leavetotal") String leavetotal,@Query("Leave_id") String leave_id);

    @POST("emp/index.php?tag=DailyAccReport")
    Observable<ResponseAccountReport> AccountReportApi(@Query("business_id") String business_id);

    @POST("booking/index.php?tag=synBillingServices")
    Observable<ResponseModel1> BillingServiceApiMethod(@Query("Bookingid") String Bookingid,
                                                       @Query("productid") String productid,
                                                       @Query("ETA") String ETA,
                                                       @Query("Price") String Price,
                                                       @Query("EmployeeID") String EmployeeID,
                                                       @Query("Bussiness_id") String Bussiness_id);


    @POST("booking/index.php?tag=synRemoveServices")
    Observable<ResponseModel> RemoveBilingServiceApiMethod(@Query("Reason") String reason,
                                                            @Query("Bookingid") String bookingId,
                                                            @Query("ProductID") String productid,
                                                            @Query("Bussiness_id") String Bussiness_id);

    @POST("booking/index.php?tag=CancelBooking")
    Observable<ResponseModel> syncCancelBookingApiMethod(@Query("Salon_ID") String business_id,
                                                         @Query("Booking_id") String booking_id,
                                                         @Query("Reason") String reason,
                                                         @Query("Bussiness_id") String Bussiness_id);

    @POST("booking/index.php?tag=payment")
    Observable<ResponseModel> syncPaymentApiMethod(@Query("Bookingid") String Bookingid,
                                                   @Query("PaymentType") String PaymentType,
                                                   @Query("Discount") String Discount,
                                                   @Query("TaxAmount") String TaxAmount,
                                                   @Query("Recd_amt") String Recd_amt,
                                                   @Query("IsWaivedOff") String IsWaivedOff,
                                                   @Query("Unpiad_Amount") String Unpiad_Amount,
                                                   @Query("RPBalPaid") String RPBalPaid,
                                                   @Query("Coupon_id") String Coupon_id,
                                                   @Query("Paid_Unpiad") String Paid_Unpiad,
                                                   @Query("Amount") String Amount);



    @POST("booking/index.php?tag=finalSync")
    Observable<ResponseModel> FinalSyncPaymentApiMethod(@Query("Bookingid") String BookingCode,
                                                        @Query("EnduserID") String EnduserID,
                                                        @Query("Salon_ID") String SalonId,
                                                        @Query("TimeSlot") String TimeSlot,
                                                        @Query("Empid") String Empid,
                                                        @Query("Services") String Services,
                                                        @Query("MobileNo") String MobileNo,
                                                        @Query("Remark") String Remark,
                                                        @Query("PrevBooking") String PrevBooking,
                                                        @Query("BookingDate") String BookingDate,
                                                        @Query("SeatNo") String SeatNo,
                                                        @Query("Assign_book") String Assign_book,
                                                        @Query("PaymentType") String PaymentType,
                                                        @Query("Discount") String Discount,
                                                        @Query("TaxAmount") String TaxAmount,
                                                        @Query("Recd_amt") String Recd_amt,
                                                        @Query("IsWaivedOff") String IsWaivedOff,
                                                        @Query("Unpiad_Amount") String Unpiad_Amount,
                                                        @Query("RPBalPaid") String RPBalPaid,
                                                        @Query("Coupon_id") String Coupon_id,
                                                        @Query("Paid_Unpiad") String Paid_Unpiad,
                                                        @Query("Amount") String Amount);

    @POST("Promotion/index.php?tag=latestOfferList")
    Observable<ResponseLatestOfferList> latestOfferApi(@Query("business_id") String business_id);

    @POST("Promotion/index.php?tag=upcomingBday")
    Observable<ResponseUpComingBirthday> upComingBirthdayOfferApi(@Query("business_id") String business_id);

    @POST("Promotion/index.php?tag=upcomingani")
    Observable<ResponseUpComingBirthday>upComingAniApi(@Query("business_id")  String business_id);

    @POST("Promotion/index.php?tag=promo")
    Observable<ResponseCreatePromotion>upCreateOfferApi(@Query("mode") String mode ,
                                                        @Query("fabkut_offer_id") String fabkut_offer_id,
                                                        @Query("fabkut_offer_code") String fabkut_offer_code,
                                                        @Query("fabkut_offer_name") String fabkut_offer_name,
                                                        @Query("fabkut_offer_desc") String fabkut_offer_desc,
                                                        @Query("fabkut_offer_pic") String fabkut_offer_pic,
                                                        @Query("fabkut_offer_start") String fabkut_offer_start,
                                                        @Query("fabkut_offer_end") String fabkut_offer_end,
                                                        @Query("fabkut_offer_price") String fabkut_offer_price,
                                                        @Query("fabkut_offer_disc_perc") String fabkut_offer_disc_perc,
                                                        @Query("active") String active,
                                                        @Query("business_id") String business_id,
                                                        @Query("fabkut_offer_type_id") String fabkut_offer_type_id);

    @POST("Promotion/index.php?tag=Promorun")
    Observable<ResponseModel1>runOfferApi(@Query("mode") String mode,@Query("fabkut_offer_id") String fabkut_offer_id
            ,@Query("endusercode") String endusercode,@Query("to_all") String to_all,@Query("active") String active,
                           @Query("business_id") String business_id);

    @POST("Promotion/index.php?tag=selectCoupon")
    Observable<ResponsePomoCode> selectPromoCode(@Query("business_id") String businessId, @Query("endusercode") String endUserCode);

    @POST("Promotion/index.php?tag=bookingCoupon")
    Observable<ResponseModel> bookingCoupon(@Query("BookingCode") String BookingCode, @Query("fabkut_offer_id")  String fabkut_offer_id,@Query("business_id") String business_id);







   /* Observable <ResponseItemMasterInsert>itemMasterInsertApiMethod(@Query("") String itemFor, @Query("") String itemBrand, @Query("") String itemType, @Query("") String itemCategory, @Query("") String itemCode, @Query("") String itemName, @Query("") String unit, @Query("") String itemColor, @Query("") String barcode, @Query("") String itemCost, @Query("") String itemRetailPrice, @Query("") String minValue, @Query("") String maxValue);


    Observable <ResponseItemBrand> brandtApiMethod();

    Observable<ResponseItemType> itemTypeApiMethod();

    Observable<ResponseItemCategory> itemCategoryApiMethod();


    Observable<ResponseUnitValue> unitValueApiMethod(@Query("") String unit);

    Observable<ResponseVendor> vendorListApiMethod(String businessId);

    Observable<ResponseItemColor> itemColorApiMethod();

    Observable<ResponseOrderInsert> OrderInsertApiMethod(@Query("") String poType,@Query("") String selectVendor,@Query("") String itemType,@Query("") String itemCategory,@Query("") String itemCode,@Query("") String item,@Query("") String unit ,@Query("") String unitValue,@Query("") String itemCost,@Query("") String itemMRP,@Query("") String remark);

    Observable<ResponseAddContractInsert> addContractInsertApiMethod(@Query("") String selectVendor,@Query("") String brand,@Query("") String itemType,@Query("") String itemCategory,@Query("") String item,@Query("") String decription,@Query("") String unit,@Query("") String unitValue,@Query("") String itemCost,@Query("") String itemMRP,@Query("") String startDate,@Query("") String endDate);

    Observable<ResponseVendorInsert> vendorInsertApiMethod(String vendorName, String code, String tin, String panNo, String contact, String phone, String deliveryMode, String agentName, String paymentMode, String address1, String address2, String state, String city, String location, String zipCode);

*/


  /*  @POST("fabkut/admin/index.php?tag=dataentry")
    Observable<ResponseModelDataEntry> dataEntryApiMethod(@Query("user_id") int user_id, @Query("city_Id") int city_id, @Query("location_Id") int location_id, @Query("business_Name") String business_name, @Query("Contact_Person") String contact_persion, @Query("contact_No") String contact_no, @Query("business_email_id") String business_email, @Query("contact_No1") String contact_no1, @Query("address1") String address1, @Query("Land_mark") String landmark);


    //tags are key on server...

    @POST("fabkut/admin/index.php?tag=MarketingSalonEmployeeInsert")
    Observable<ResponseLogin> salonEmployeeApiMethod(@Query("business_id") String business_id, @Query("emp_name") String emp_name, @Query("emp_contact_no") String emp_contact, @Query("spcst") String emp_spec, @Query("adress1") String emp_addr);

    @POST("fabkut/admin/index.php?tag=city")
    Observable<ResponseCity> cityApiMethod();

    @POST("fabkut/admin/index.php?tag=location")
    Observable<ResponseLocation> locationApiMethod(@Query("city_id") int city_id);

    @POST("fabkut/admin/index.php?tag=GetSalonFacilityList")
    Observable<ResponseSalonFacility> salonfacilityShowApiMethod();

    @POST("fabkut/admin/index.php?tag=marketinglist")
    Observable<ResponseMarketinList> marketinglistApiMethod(@Query("user_id") String user_id);


    //tags are key on server...*/
}