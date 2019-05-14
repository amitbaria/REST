<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

 <style>
            .button {
                cursor: pointer;
                color: blue;
            }
            td,th{
                border: 1px solid gray;
                width: 25%;
                text-align: left;
            }
            table {
                width: 600px;
            }
            
            div#editformdiv
            {
               display:none;
            }
            
            div#submituploadDocumentForm
            {
              display:none;
            }
            
            
        </style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
 
        <script type="text/javascript">
        
        
         
        
     
             //step 1 ... Initialize application variable      
     var app = angular.module("CompanyManagement", []);
         
            //  step 2...   Controller Part
            app.controller("CompanyManagementController", function($scope, $http) {
         
                // step 3... Initialize page with default data which is blank in this example
                
                 $scope.object =[];
                
                
                $scope.companylist=[];
                $scope.companylist1=[];
                
                $scope.form =
                {
                	companyname: "",
                	company_inception: "",
                	shareprice: -1.0,
                	companyid: -1
                    	
                	
                };
                
                
                
                
              
           	
                _refreshPageData();
                
                function _refreshPageData() {
                    $http({
                        method : 'GET',
                        url : '/rest/getsortedCompaniesByCompnanyId'
                    }).then(function successCallback(response) {
                    	
                    	// if data available.............
                        $scope.companylist = response.data;
                    }, function errorCallback(response) {
                    	
                    	
                    	   // if data nonavailable
                          if( response.statusText==undefined)
                    	     {
                        	  
                        	    
                        	     $scope.companylist = [];
                    	   
                    	    }
                    	   
                    	   console.log( response.data);
                    	   
                          if(response.data=="")
                        	  {
                        	    alert("No More Companies available");
                        	    $scope.companylist = [];
                        	    $("#editformdiv").hide();
                        	   
                 
                        	    
                        	  }
                    	   
                    });
                }
                
              
                // for remove url....
                $scope.removeCompany=  function(company)
                {
                	
                	alert(company);
                	 console.log("remove.......");
                	$http({
                		
                		method: 'DELETE',
                		url: '/rest/deleteCompany/'+company.companyid
                	
                	}).then(_success,_error);
                	
                };
                
                
                function _success(response)
                {
                	
                	console.log(response.statusText);
                	
                	_refreshPageData();	
                	$("#submituploadDocumentForm").hide();
                	
                }
                
                function _error(response)
                {
                	
                	
                	if(response.statusText=="")
                		{
                		  alert("Sorry Data not available to Sort");
                		  $("#editformdiv").hide();	
                		
                		}
                	
                }
                
                
                // on Click of edit Link........
               /* $scope.editCompany=function(company)
                {
                  
                   getCompanyDetailById
                   
                   $http({
                	       method:'GET',
                	       url:'rest/getCompanyDetailById/'+company.companyid
                	   
                         }).then(_fillFormData,_error);
                   
                   
                };
                */
                
                
                // company is a json object
             $scope.fillFormData= function(company)
              	{
                	 alert();
            	        $("#editformdiv").show();
		            	 
            	         $scope.form.companyid=company.companyid;
		            	 $scope.form.companyname=company.companyname;
		            	 $scope.form.company_inception=company.company_inception;
		            	 $scope.form.shareprice=company.shareprice;
		            	
            	  
             	 };
             	 
             	 
             	  // sending request into the form of JSON to update Company Details
             	 
             	  $scope.editFormSubmit= function()
             	  {
             		
             		 
             		$http({
             			method:'PUT',
             			url:'rest/updateCompany',
             			data:angular.toJson($scope.form),
             			
             			 headers : {
                            'Content-Type' : 'application/json'
                        }
             			
             			
             		}).then(_success,_error);  
             		  
             		  
             	  };
             	  
             	  
             	  // sort companies 
             	 $scope.sortCompaniesDetails = function()
             	 {
             		$http({ 
             			 method:'GET',
             			 url:'/rest/sortByCompany'
             			 
             		}).then( function successCallback(response) {
                    	
                    	// if data available.............
                        $scope.companylist = response.data;
                           },_error);
             		 
             		 
             	 };
             	 // sort companies by share price
             	  // sort companies 
             	 $scope.sortCompaniesBySharePrice = function()
             	 {
             		$http({ 
             			 method:'GET',
             			 url:'/rest/getsortedCompaniesBySharePrice'
             			 
             		}).then( function successCallback(response) {
                    	
                    	// if data available.............
                        $scope.companylist = response.data;
                           },_error);
             	 };
             	 
             	
            	  // delete check box selected companies
            	 $scope.selectedItems=function()
                  {
            		  
            		
            		 $.each($("input[name='check']:checked"),function() {
                     	
            			 
            			    
                 	         //CompaniesObject.push($(this).val());
                 	         
                 	        var obj = JSON.parse($(this).val());
                 	        
                 	             $scope.form.companyid=obj.companyid;
		            	 $scope.form.companyname=obj.companyname;
		            	 $scope.form.company_inception=obj.company_inception;
		            	 $scope.form.shareprice=obj.shareprice;
                 	          
                 	    
                 	         
                 	        $scope.companylist1.push($scope.form);
                  	
            		        });
            		 
            		 
            		 
            		// var obj = JSON.parse('{ "name":"John", "age":30, "city":"New York"}');
            		 
            		/* if(CompaniesObject.length>0)
            			 
            			 {
            			 
            				 var jsonString="";
            				 
            				 $scope.companylist1=[];
            			 
            		 
            		 				for(i=0;i<CompaniesObject.length;i++)
            			 				{
            			 
				            			   	if(i== (CompaniesObject.length-1) )
				            				   {
				            			   		jsonString=jsonString.concat(CompaniesObject[i]);
				            				   }
				            			   	else
				            			   		{
				            			   	    	jsonString=jsonString.concat(CompaniesObject[i]+",")
				            			   		}
				            			  
				            			  
				            			  
				            			 console.log(CompaniesObject[i].companyname);
				            			  
				            			  
				            			  
            		 					
				            			 
            			 				}
            		 				
            		 				
            		 				*/
            		 				
            		 				
            		 				console.log($scope.companylist1);
            		 				
            		 			
            		 	
            		 				console.log("             "+$scope.form.shareprice);
            		 				
            		 				 $http({
                     		    		
            		            		  method:'DELETE',
            		          			  url:'rest/deleteSelectedCompanies',
            		          			 // data:angular.toJson($scope.companylist1),
            		          			  data: $.param($scope.companylist1),
            		          			 
            		          			
            		          			    headers : {
            		                       			   'Content-Type' : 'application/json'
            		                          		   }
            		          			
            		          			
            		          		       }).then(_success,_error);  		
            		 				
            			 //} // if close
            		
            		 
            		
               };
               
               
                 
               
                 $scope.uploadDocumentForm=function(company)
                 {
                	 
                	 $("#companyIdtext").val(company.companyid);
                	 $("#submituploadDocumentForm").show();
                	 
                 };
                 
                 
                 $scope.uploadDocument=function()
                 {
                	 
                	   alert(companyId);
                	 
                	 
                 };
                 
                 
                 
                 $scope.dounloadAttachment =   function(attachments)
                   {
                	 $http({
                		 method:'GET',
                       url:'rest/downloadAttachmentFile/companyId/'+attachments.comapnayId+'/attachmentId/'+attachments.attachmentid+'/filename/'+attachments.attachmentFileName,
                
                     // url: '/rest/downloadAttachmentFile/companyId/1/attachmentId/1/filename/lic.jpg',
                      responseType: 'arraybuffer' 
                	 
                	 }).success(function (data, status, headers) {
                	        headers = headers();
                	        
                	        var filename = headers['x-filename'];
                	        var contentType = headers['content-type'];
                	 
                	        var linkElement = document.createElement('a');
                	        try {
                	            var blob = new Blob([data], { type: contentType });
                	            var url = window.URL.createObjectURL(blob);
                	 
                	            linkElement.setAttribute('href', url);
                	            linkElement.setAttribute("download", filename);
                	 
                	            var clickEvent = new MouseEvent("click", {
                	                "view": window,
                	                "bubbles": true,
                	                "cancelable": false
                	            });
                	            linkElement.dispatchEvent(clickEvent);
                	        } catch (ex) {
                	            console.log(ex);
                	        }
                	    }).error(function (data) {
                	        console.log(data);
                	    });
                	 
                   };
                   
                   
                 /*  function downloadSuccess(response)
                   {
                   	
                	  
                 	/*alert("downloaded Successfully");
                   	var file = new Blob([response.data], { type: 'text/plain' });
                    var a = document.createElement('a');
                    a.href = URL.createObjectURL(file);
                    a.download = "filename.txt";
                    a.click();
                    
                    
                    
                    
                	   headers = headers();
                	   
                       var filename = headers['x-filename'];
                       var contentType = headers['content-type'];
                    
                      
                    
                   } 
                   */
                   
            });
            
            
            $( document ).ready(function() { 
            
           $("#selecteAllCheckBox").click( function()
            {
        	   
        	   if($(this).prop("checked") == true)
        		   {
        		   $('input[name=check]').prop('checked', true);
        		   }
        	   else
        		   {
        		   $('input[name=check]').prop('checked', false);
        		   }
        	   
            }); 
           
           
            }); // document ready close
            
            
          /* 	 for(i=0;i<CompaniesObject.length;i++)
           		 {
           		 console.log( CompaniesObject);
           		 
           		 }
          
          */
           	 
           
            
            
            function  hideCompanyEditForm()
    		{
    	         $("#editformdiv").hide();	
    	
    		}     
 </script>




</head>
<body   ng-app="CompanyManagement" ng-controller="CompanyManagementController">
 
   Welcome <b> ${UserName} </b> |    <a href="/logout">Logout</a>
 <!--   <a href="/details">Show All Companies On Next Page</a>  -->
  
  
   <center>
   <div>
  <table>
            <tr>
                <th> <input type="checkbox"  id="selecteAllCheckBox"></input> Select All
                <th>Company Name    | <a href="#" ng-click="sortCompaniesDetails()"> Sort Companies Names</a>  </th>
                <th> Comapany Inception Date</th>
                <th>Company Share price    |  <a href="#" ng-click="sortCompaniesBySharePrice()"> Sort By Share Price</a></th>
                <th> Download Attachments </th>
                
                <th> Add/Edit Attachments </th>  
                
                <th> EDTI  |  Remove </th>
                
            </tr>
 
            <tr ng-repeat="company in companylist">
                
               <td> <input type="checkbox"  value={{company}}    name='check' /></td>
                
                <td>{{ company.companyname }}</td>
                <td>{{ company.company_inception }}</td>
                <td>{{ company.shareprice }}</td>
                
                <!--  sub loop   for attachments-->
                <td>
                 
                <div ng-repeat="attachments in company.companyInfoWithAttachment"> 
                     
               <!--   <tr><td>{{ attachments.attachmentFileName}}</td></tr>  -->
                
                 <div><a href="#"  ng-click="dounloadAttachment(attachments)">{{ attachments.attachmentFileName}} </a></div>
                
                </div>
                </td> 
                
               <td>   <a href="#" ng-click="uploadDocumentForm(company)" class="button">Upload</a> </td>
                <td>
               <a href="#" ng-click="fillFormData( company )" class="button">Edit</a> | <a href="#" ng-click="removeCompany( company )"  class="button">Remove</a>
                </td>
                
                <!--  take an array of company objects... -->
                
                
            </tr>
        </table>  
        
          <div><a href="#" ng-click="selectedItems()"> Delete   Selected </a> </div>
   </div>
      <div  id="editformdiv">  
            <span> <h1> Edit/Add Company Detail</h1></span> <a href="javascript:hideCompanyEditForm()">   Hide Edit Form </a>
          <form ng-submit="editFormSubmit()">
           		 Company Name<input type="text" ng-model="form.companyname" size="20"/> <br>
           		 Company Inception Date<input type="text" ng-model="form.company_inception" size="20"/><br/>
           	     Company Share Price<input type="text" ng-model="form.shareprice" size="20"/><br/>
           			 <input type="hidden"  ng-model="form.companyid"/>
            <input type="submit"  value="Submit" />
          </form>
     </div>
     
     
       <div id="submituploadDocumentForm">
      
         <form action="/rest/uploadFileFromGUI"  method="post"  enctype="multipart/form-data">
               <input type="file"  name="file"/>
                <input type="hidden"  name="companyid"  id="companyIdtext"  value="" />
                
                <input type="submit"  value="Upload"/>
               
              
         
         </form>
      
         </div>  
     
   
   
   
   
    </center>
    
   
 

</body>
</html>