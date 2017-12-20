<?php
if($_SERVER['REQUEST_METHOD']=='GET'){

include 'databaseconfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName );
 //$con = new mysqli("localhost","root","","mobilewebfinal");
 //check if connection is successful

      if(!$con){
        echo "Database connection failed";
        //exit();
      }
        // write query


 $uname = $_GET['username'];
 $useremail = $_GET['email'];
 $pwd = $_GET['password'];
 $phoneNo = $_GET['phonenumber'];
 $userAge= $_GET['age'];
 $organization = $_GET['organization'];

 $CheckSQL = "SELECT * FROM userlogintable WHERE email='$useremail'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){

 echo 'Email Already Exists';

 }
else{ 
$Sql_Query = "INSERT INTO userlogintable (username,email,password,phonenumber,age,organization) values ('$uname','$useremail','$pwd','$phoneNo','$userAge','$organization')";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Registration Successful';
}
else
{
 echo 'Something went wrong';
 }
 }
}
 mysqli_close($con);
?>