<?php
    // array for JSON response
    $response= array();
    // include db connect class
    require_once__DIR__ . '/db_connect.php';
    // connecting to db
    $db= newDB_CONNECT();
    // check for post data
    if(isset($_GET["patient_id"])) {
        $patient_id= $_GET['patient_id'];
        // get test results from table
        $result= mysql_query("SELECT *FROM test_result WHERE patient_id = $patient_id");
        if(!empty($result)) {
            // check for empty result
            if(mysql_num_rows($result) > 0) {
                $result= mysql_fetch_array($result);
                $patient= array();
                $patient["patient_id"] = $result["patient_id"];
                $patient["avg_length"] = $result["avg_length"];
                $patient["avg_cadence"] = $result["avg_cadence"];
                $patient["avg_degree"] = $result["avg_degree"];
                $patient["dcd_length"] = $result["dcd_length"];
                $patient["dcd_cadence"] = $result["dcd_cadence"];
                $patient["dcd_degree"] = $result["dcd_degree"];
                // success
                $response["success"] = 1;
                // user node
                $response["patient"] = array();
                array_push($response["test_result"], $test_result);
                // echoing JSON response
                echojson_encode($response);
            } else{
                // no results found
                $response["success"] = 0;
                $response["message"] = "No results found";
                // echo no users JSON 
                echojson_encode($response); 
            } 
        } else{ 
            // no results found
            $response["success"] = 0; 
            $response["message"] = "No results found";
            // echo no users JSON 
            echojson_encode($response); 
        } 
    } else{ 
        // required field is missing 
        $response["success"] = 0; 
        $response["message"] = "Required field(s) is missing"; 
        // echoing JSON response 
        echojson_encode($response); 
    } 
    ?>