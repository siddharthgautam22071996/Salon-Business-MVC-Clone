package com.admin.Model;

import java.util.List;

public class AllSaloonModel {
    /**
     * MESSAGE : SUCCESS
     * STATUS : 200
     * data : [{"business_id":"1","business_Name":"Ashish's Spa","Contact_Person":"Ashish","contact_No":"8460638801","business_email_id":"ashishspa@fabkut.com","Salon_password":"123","no_of_chairs":"2","servicetax":"0","opening_hours":"00:00:00.0000000","closing_hours":"00:00:00.0000000","tagline":"We do best work","no_of_emp":"4","city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":"B Block","latitude":"0","longitute":"0","active":"0","status":"1"},{"business_id":"2","business_Name":"The Roots Unisex Salon, Spa & Ayurveda","Contact_Person":"Not yet Available","contact_No":"0123456789","business_email_id":"Rootsusp@fabkut.com","Salon_password":"123","no_of_chairs":"5","servicetax":"0","opening_hours":"06:00:00.0000000","closing_hours":"23:59:00.0000000","tagline":"all set","no_of_emp":"8","city_Name":"Bangalore","location_Name":"Kaikondarahalli (Sarjapur Road)","address1":"No 51, 1st Floor, BRS Towers, Sarjapur Main Rd,Above ICICI Wealth Management Bank, Kaikondrahalli","latitude":"","longitute":"","active":"1","status":"1"},{"business_id":"3","business_Name":"Angel Salon","Contact_Person":"Angel Pal","contact_No":"7388500441","business_email_id":"angel@fabkut.com","Salon_password":"123","no_of_chairs":"2","servicetax":"0","opening_hours":"08:00:00.0000000","closing_hours":"22:00:00.0000000","tagline":"Be Happy","no_of_emp":"4","city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":"Malviya Nagar","latitude":"","longitute":"","active":"1","status":"1"},{"business_id":"4","business_Name":"bew","Contact_Person":"Angel Pal","contact_No":"7388500441","business_email_id":"bew@fabkut.com","Salon_password":"123","no_of_chairs":"2","servicetax":"0","opening_hours":"08:00:00.0000000","closing_hours":"22:00:00.0000000","tagline":"Be Happy","no_of_emp":"4","city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":"Malviya Nagar","latitude":"","longitute":"","active":"1","status":"1"},{"business_id":"5","business_Name":"divtest","Contact_Person":"98989898","contact_No":"98989898","business_email_id":"div@fabkut","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"6","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"7","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"8","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"9","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"10","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"11","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"12","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"13","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"14","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":"8","servicetax":"0","opening_hours":"00:00:00.0000000","closing_hours":"00:00:00.0000000","tagline":null,"no_of_emp":"0","city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":"0","longitute":"0","active":null,"status":null},{"business_id":"15","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"16","business_Name":"divtest","Contact_Person":"98989898","contact_No":"98989898","business_email_id":"div@fabkut","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"17","business_Name":"divtest","Contact_Person":"98989898","contact_No":"98989898","business_email_id":"div@fabkut","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"18","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"19","business_Name":"divtest","Contact_Person":"98989898","contact_No":"98989898","business_email_id":"div@fabkut","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"20","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"21","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"22","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"23","business_Name":"divtest","Contact_Person":"98989898","contact_No":"98989898","business_email_id":"div@fabkut","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"24","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"25","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"26","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"27","business_Name":"div","Contact_Person":"div","contact_No":"1231112","business_email_id":"div@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"28","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"29","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"30","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"31","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"32","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"33","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"34","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"35","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"36","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"37","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"38","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"39","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"40","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"41","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"42","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"43","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"44","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"45","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"46","business_Name":"$business_Name","Contact_Person":"$contact_person","contact_No":"$contact_No","business_email_id":"$business_email_id","Salon_password":"$password","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"47","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"48","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"49","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"50","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"51","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"52","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"53","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"54","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"55","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"56","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"57","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"58","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"59","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"60","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"61","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"62","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"63","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"64","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null},{"business_id":"65","business_Name":"div","Contact_Person":"siv","contact_No":"987877877","business_email_id":"siv@fabkut.com","Salon_password":"123","no_of_chairs":null,"servicetax":null,"opening_hours":null,"closing_hours":null,"tagline":null,"no_of_emp":null,"city_Name":"Kanpur","location_Name":"Kidwai Nagar","address1":null,"latitude":null,"longitute":null,"active":null,"status":null}]
     */

    private String MESSAGE;
    private String STATUS;
    private List<DataBean> data;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * business_id : 1
         * business_Name : Ashish's Spa
         * Contact_Person : Ashish
         * contact_No : 8460638801
         * business_email_id : ashishspa@fabkut.com
         * Salon_password : 123
         * no_of_chairs : 2
         * servicetax : 0
         * opening_hours : 00:00:00.0000000
         * closing_hours : 00:00:00.0000000
         * tagline : We do best work
         * no_of_emp : 4
         * city_Name : Kanpur
         * location_Name : Kidwai Nagar
         * address1 : B Block
         * latitude : 0
         * longitute : 0
         * active : 0
         * status : 1
         */

        private String business_id;
        private String business_Name;
        private String Contact_Person;
        private String contact_No;
        private String business_email_id;
        private String Salon_password;
        private String no_of_chairs;
        private String servicetax;
        private String opening_hours;
        private String closing_hours;
        private String tagline;
        private String no_of_emp;
        private String city_Name;
        private String location_Name;
        private String address1;
        private String latitude;
        private String longitute;
        private String active;
        private String status;

        public String getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(String business_id) {
            this.business_id = business_id;
        }

        public String getBusiness_Name() {
            return business_Name;
        }

        public void setBusiness_Name(String business_Name) {
            this.business_Name = business_Name;
        }

        public String getContact_Person() {
            return Contact_Person;
        }

        public void setContact_Person(String Contact_Person) {
            this.Contact_Person = Contact_Person;
        }

        public String getContact_No() {
            return contact_No;
        }

        public void setContact_No(String contact_No) {
            this.contact_No = contact_No;
        }

        public String getBusiness_email_id() {
            return business_email_id;
        }

        public void setBusiness_email_id(String business_email_id) {
            this.business_email_id = business_email_id;
        }

        public String getSalon_password() {
            return Salon_password;
        }

        public void setSalon_password(String Salon_password) {
            this.Salon_password = Salon_password;
        }

        public String getNo_of_chairs() {
            return no_of_chairs;
        }

        public void setNo_of_chairs(String no_of_chairs) {
            this.no_of_chairs = no_of_chairs;
        }

        public String getServicetax() {
            return servicetax;
        }

        public void setServicetax(String servicetax) {
            this.servicetax = servicetax;
        }

        public String getOpening_hours() {
            return opening_hours;
        }

        public void setOpening_hours(String opening_hours) {
            this.opening_hours = opening_hours;
        }

        public String getClosing_hours() {
            return closing_hours;
        }

        public void setClosing_hours(String closing_hours) {
            this.closing_hours = closing_hours;
        }

        public String getTagline() {
            return tagline;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
        }

        public String getNo_of_emp() {
            return no_of_emp;
        }

        public void setNo_of_emp(String no_of_emp) {
            this.no_of_emp = no_of_emp;
        }

        public String getCity_Name() {
            return city_Name;
        }

        public void setCity_Name(String city_Name) {
            this.city_Name = city_Name;
        }

        public String getLocation_Name() {
            return location_Name;
        }

        public void setLocation_Name(String location_Name) {
            this.location_Name = location_Name;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitute() {
            return longitute;
        }

        public void setLongitute(String longitute) {
            this.longitute = longitute;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
