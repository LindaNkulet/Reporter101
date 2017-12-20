<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'databaseconfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName );
 //$con = new mysqli("localhost","root","","mobilewebfinal");
 //check if connection is successful

      if(!$con){
        echo "Database connection failed";
        //exit();
      }
        // write query

//  $DefaultId = 0;
//  $GetOldIdSQL ="SELECT userId FROM userlogintable";
 
//  $Query = mysqli_query($con,$GetOldIdSQL);
 
//  while($row = mysqli_fetch_array($Query)){
 
//  $DefaultId = $row['userId'];
 
//  }
 //$ImagePath = "images/$DefaultId.png";
 
 //$ServerURL = "https://linda-delishoi.000webhostapp.com/mobilewebfinal/$ImagePath";

 $complaint = $_POST['complaint'];
 $image= $_POST['media'];
 $latitude= $_POST['Latitude'];
 $longitude= $_POST['Longitude'];
 
$Sql_Query = "INSERT INTO complaints (complaint,media,Latitude,Longitude) values ('$complaint', '$image','$latitude','$longitude')";
//$Sql_Query = "INSERT INTO complaints (complaint,media) values ('$complaint', '$image')";

 if(mysqli_query($con,$Sql_Query))
{
//file_put_contents($ImagePath,base64_decode($image));

 echo "Post uploaded successfully.";
}
else
{
 echo 'Failed to upload.';
 }
 mysqli_close($con);
} else{
    echo "Invalid request";
}

?>