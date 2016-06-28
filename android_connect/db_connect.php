<?php
    /**
     * A class file to connect to database
     */
    classDB_CONNECT {
        // constructor
        function__construct() {
            // connecting to database
            $this->connect();
        }
        // destructor
        function__destruct() {
            // closing db connection
            $this->close();
        }
        /**
         * Function to connect with database
         */
        functionconnect() {
            // import database connection variables
            require_once__DIR__ . '/db_config.php';
            // Connecting to mysql database
            $con= mysql_connect(DB_SERVER, DB_USER, DB_PASSWORD) ordie(mysql_error());
            // Selecing database
            $db= mysql_select_db(DB_DATABASE) ordie(mysql_error()) ordie(mysql_error());
            // returing connection cursor 
            return$con; 
        } 
        /** 
         * Function to close db connection 
         */
        functionclose() { 
            // closing db connection 
            mysql_close(); 
        } 
    } 
    ?>