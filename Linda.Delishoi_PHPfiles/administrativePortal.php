        <!DOCTYPE html>
      <html>
        <head>
        <meta charset="utf-8">
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>Reporter101 by Linda<</title>

        <script type="text/javascript">
        function searchTable() {
    	var input, filter, found, table, tr, td, i, j;
    	input = document.getElementById("myInput");
    	filter = input.value.toUpperCase();
    	table = document.getElementById("myTable");
    	tr = table.getElementsByTagName("tr");
    	for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td");
        for (j = 0; j < td.length; j++) {
            if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                found = true;
            }
        }
        if (found) {
            tr[i].style.display = "";
            found = false;
        } else {
            tr[i].style.display = "none";
        	}
    	}
		}
          </script>
        

      <!--sETTING THE DIMENSIONS OF THE MAP-->
        <style>
             table, td, th {
      border: 1px solid black;
       padding: 15px;
  }

  table {
      border-collapse: collapse;
      width: 100%;
  }

  th {
      text-align: left;
      background-color: #4CAF50;
      color: white;
  }
  tr:hover {background-color: #f5f5f5;}
  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    color: #4CAF50;
    overflow: hidden;
    background-color: #dddddd;
}

li {
    float: left;
}
}

li a {
    display: block;
    padding: 8px;
}
li a, .dropbtn {
    display: inline-block;
    color: #4CAF50;
    text-align: center;
    padding: 20px 18px;
    text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
    background-color: #f9f9f9;
}

li.dropdown {
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}

input[type=text] {
    width: 70%;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
    background-color: #f5f5f5;
    background-position: 10px 10px; 
    background-repeat: no-repeat;
    padding: 7px ;
    margin-left: 60%;
    margin-top: 2%;
}

img {
    height: 150px;
    width: 200px; 
}
          </style>

<!--padding: 12px 20px 12px 40px;-->

        </head>
        <body>
          
          <div data-role="page">

          	<!-- <div data-role="header">
          		<h3>ASHESI MONEY</h3>
          	</div><!-- /header -->

          	<div data-role="content">
            <div data-role="navbar" >
            <ul>
              <li  ><h3>REPORTER101 ADMIN PORTAL</h3></li>
              <li style="float:right"><a class="active" href="#logout">LOGOUT</a></li>
              <li><input class="searchbar" placeholder="Search..." type="text" id="myInput"  onkeyup="searchTable()" /></li>
              
              </ul>
              
            </div>
   <div id="table" class="transactions-table" style="overflow-x:auto;">          
           <?php


// php populate html table from mysql database

$hostname = "localhost";
$username = "id3936488_root";
$password = "kisses100";
$databaseName = "id3936488_mobilewebfinal";


// connect to mysql
$connect = mysqli_connect($hostname, $username, $password, $databaseName);

// mysql select query

$query = "SELECT * FROM complaints";
/*$query="SELECT 
    userlogintable.userId, userlogintable.username,userlogintable.email, userlogintable.phonenumber,userlogintable.age, userlogintable.organization, complaints.complaint, complaints.media,complaints.Date
FROM 
    userlogintable
INNER JOIN 
   complaints 
ON
    userlogintable.userId=data.userID";*/


// result for method one
$result1 = mysqli_query($connect, $query);

$dataRow = "";

 
  /*while($row1 = mysqli_fetch_array($result1)):;
      $dataRow =$dataRow. "<tr>
                <td> ".$row1[0]." </td>
                <td> ".$row1[1]." </td>
                <td> ".$row1[2]." </td>
                <td> ".$row1[4]." </td>
                <td> ".$row1[5]." </td>
                
                
            </tr>";
    endwhile;*/
    
    echo "<table border='1' width='100%' id='myTable' >
    <tr>
    <th>OffenceID</th>
    <th>Description</th>
    <th>Image</th>
    <th>Latitude</th>
    <th>Longitude</th>
    <th>Date</th>
    </tr>"; 
    while($row = mysqli_fetch_array($result1)) { 
        echo "<tr>";
        echo "<td>" . $row['complaintID'] . "</td>";
        echo "<td>" . $row['complaint'] . "</td>";
        $dbimage = $row['media'];
        echo "<td><img src='data:image/jpeg;base64,".$row['media']."'></td>";
        echo "<td>" . $row['Latitude'] . "</td>";
        echo "<td>" . $row['Longitude'] . "</td>";
        echo "<td>" . $row['Date'] . "</td>";
        
        echo "</tr>"; 
    }
   
echo "</table>";
    
    ?>
<!--<table border="1" id ="myTable">
	<td>OffenceID</td>
	<td>Description</td>
	<td>Media</td>
	<td>Latitude</td>
	<td>Longitude</td>
  <td>Date</td>-->
  <!--<td>Offence</td>
  <td>Image Path</td>
	<td>Date</td>-->

 
<!--</table>-->

</div><!-- /content -->


            <footer class="page-footer">
                <div class="container">
                  <div class="row">
                    <div class="col l6 s12">
                    <a href="#">Reporter101</a>, simple,secure and efficient way of getting your voice heard
                      <p class="grey-text text-lighten-4">@copyright Reporter101byLinda.All Rights Reserved. </p>
                    </div>
                  </div>
                </div>
              </footer>       

          </div><!-- /page -->
          

        </body>
      </html>