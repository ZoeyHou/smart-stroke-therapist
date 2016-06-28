<?php
    // array for response
    $response= array();
    // include db connect class
    require_once__DIR__ . '/db_connect.php';
    // connecting to db
    $db= newDB_CONNECT();
    // get all patients from patient table
    $result= mysql_query("SELECT *FROM patient") ordie(mysql_error());
    // check for empty result
    if(mysql_num_rows($result) > 0) {
        // looping through all results
        // patients node
        $response["patient"] = array();
        while($row= mysql_fetch_array($result)) {
            // temp user array
            $patient= array();
            $patient["patient_id"] = $row["patient_id"];
            $patient["patient_pwd"] = $row["patient_pwd"];
            $patient["patient_name"] = $row["patient_name"];
            $patient["height"] = $row["height"];
            $patient["gender"] = $row["gender"];
            $patient["weight"] = $row["weight"];
            $patient["age"] = $row["age"];
            $patient["leg_length"] = $row["leg_length"];
            // push single patient into final response array
            array_push($response["patient"], $patient);
        }
        // success
        $response["success"] = 1;
        // echoing response
        echojson_encode($response); 
    } else{ 
        // no patient found
        $response["success"] = 0; 
        $response["message"] = "No patient found";
        // echo no users JSON 
        echojson_encode($response); 
    } 
    ?>