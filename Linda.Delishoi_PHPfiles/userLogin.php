<?php

 if($_SERVER['REQUEST_METHOD']=='GET'){

 include 'databaseconfig.php';
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 //$con = mysqli_connect("localhost","root","","mobilewebfinal");
 
 
 $email = $_GET['email'];
 $password = $_GET['password'];
 
 $Sql_Query = "select * from userlogintable where email = '$email' and password = '$password' ";
 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 if(isset($check)){
 
 echo "Data Matched";
 }
 else{
 echo "Invalid Username or Password Please Try Again";
 }
 
 }else{
 echo "Check Again";
 }
mysqli_close($con);

?>