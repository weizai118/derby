/*
 
   Derby - Class org.apache.derby.client.net.ClientJDBCObjectFactoryImpl
 
   Copyright (c) 2006 The Apache Software Foundation or its licensors, where applicable.
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
 */

package org.apache.derby.client.net;

import java.rmi.UnexpectedException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.derby.client.ClientPooledConnection;
import org.apache.derby.client.am.CallableStatement;
import org.apache.derby.client.am.ClientJDBCObjectFactory;
import org.apache.derby.client.am.PreparedStatement;
import org.apache.derby.client.am.Configuration;
import org.apache.derby.client.am.LogWriter;
import org.apache.derby.client.am.Agent;
import org.apache.derby.client.am.Section;
import org.apache.derby.client.am.SqlException;
import org.apache.derby.client.am.Cursor;
import org.apache.derby.jdbc.ClientDataSource;

/**
 * Implements the the ClientJDBCObjectFactory interface and returns the classes
 * that implement the JDBC3.0/2.0 interfaces
 * For Eg. newCallableStatement would return
 * org.apache.derby.client.am.CallableStatement
 */

public class ClientJDBCObjectFactoryImpl implements ClientJDBCObjectFactory{
    /**
     * Returns an instance of org.apache.derby.client.ClientPooledConnection 
     */
    public ClientPooledConnection newClientPooledConnection(ClientDataSource ds,
            LogWriter logWriter,String user,
            String password) throws SQLException {
        return new ClientPooledConnection(ds,logWriter,user,password);
    }
    /**
     * Returns an instance of org.apache.derby.client.ClientPooledConnection 
     */
    public ClientPooledConnection newClientPooledConnection(ClientDataSource ds,
            LogWriter logWriter,String user,
            String password,int rmId) throws SQLException {
        return new ClientPooledConnection(ds,logWriter,user,password,rmId);
    }
    /**
     * Returns an instance of org.apache.derby.client.am.CallableStatement
     */
    public CallableStatement newCallableStatement(Agent agent,
            org.apache.derby.client.am.Connection connection,
            String sql,int type,int concurrency,
            int holdability) throws SqlException {
        return new CallableStatement(agent,connection,sql,type,
                concurrency,holdability);
    }
    
    /**
     * returns an instance of org.apache.derby.client.am.PreparedStatement
     */
    public PreparedStatement newPreparedStatement(Agent agent,
            org.apache.derby.client.am.Connection connection,
            String sql,Section section) throws SqlException {
        return new PreparedStatement(agent,connection,sql,section);
    }
     /**
     * returns an instance of org.apache.derby.client.am.PreparedStatement
     */
    public PreparedStatement newPreparedStatement(Agent agent,
            org.apache.derby.client.am.Connection connection,
            String sql,int type,int concurrency,int holdability,
            int autoGeneratedKeys,String [] columnNames)
            throws SqlException {
        return new PreparedStatement(agent,connection,sql,type,concurrency,
                holdability,autoGeneratedKeys,columnNames);
    }
    /**
     * returns an instance of org.apache.derby.client.net.NetConnection
     */
    public org.apache.derby.client.am.Connection newNetConnection(
            org.apache.derby.client.am.LogWriter netLogWriter,
            String databaseName,java.util.Properties properties)
            throws SqlException {
        return (org.apache.derby.client.am.Connection)
        (new NetConnection((NetLogWriter)netLogWriter,databaseName,properties));
    }
    /**
     * returns an instance of org.apache.derby.client.net.NetConnection
     */
    public org.apache.derby.client.am.Connection newNetConnection(
            org.apache.derby.client.am.LogWriter netLogWriter,
            org.apache.derby.jdbc.ClientDataSource clientDataSource,
            String user,String password) throws SqlException {
        return  (org.apache.derby.client.am.Connection)
        (new NetConnection((NetLogWriter)netLogWriter,clientDataSource
                ,user,password));
    }
    /**
     * returns an instance of org.apache.derby.client.net.NetConnection
     */
    public org.apache.derby.client.am.Connection newNetConnection(
            org.apache.derby.client.am.LogWriter netLogWriter,
            int driverManagerLoginTimeout,String serverName,
            int portNumber,String databaseName,
            java.util.Properties properties) throws SqlException {
        return (org.apache.derby.client.am.Connection)
        (new NetConnection((NetLogWriter)netLogWriter,driverManagerLoginTimeout,
                serverName,portNumber,databaseName,properties));
    }
    /**
     * returns an instance of org.apache.derby.client.net.NetConnection
     */
    public org.apache.derby.client.am.Connection newNetConnection(
            org.apache.derby.client.am.LogWriter netLogWriter,String user,
            String password,
            org.apache.derby.jdbc.ClientDataSource dataSource,
            int rmId,boolean isXAConn) throws SqlException {
        return (org.apache.derby.client.am.Connection)
        (new NetConnection((NetLogWriter)netLogWriter,user,password,dataSource,rmId,
                isXAConn));
    }
    /**
     * returns an instance of org.apache.derby.client.net.NetConnection
     */
    public org.apache.derby.client.am.Connection newNetConnection(
            org.apache.derby.client.am.LogWriter netLogWriter,
            String ipaddr,int portNumber,
            org.apache.derby.jdbc.ClientDataSource dataSource,
            boolean isXAConn) throws SqlException {
        return (org.apache.derby.client.am.Connection)
        new NetConnection((NetLogWriter)netLogWriter,ipaddr,portNumber,dataSource,
                isXAConn);
    }
    /**
     * returns an instance of org.apache.derby.client.net.NetResultSet
     */
    public org.apache.derby.client.am.ResultSet newNetResultSet(Agent netAgent,
            org.apache.derby.client.am.MaterialStatement netStatement,
            Cursor cursor,int sqlcsrhld,int qryattscr,int qryattsns,
            int qryattset,long qryinsid,int actualResultSetType,
            int actualResultSetConcurrency,
            int actualResultSetHoldability) throws SqlException {
        return new NetResultSet((NetAgent)netAgent,(NetStatement)netStatement,cursor,sqlcsrhld,qryattscr,
                qryattsns,qryattset,qryinsid,actualResultSetType,
                actualResultSetConcurrency,actualResultSetHoldability);
    }
    /**
     * returns an instance of org.apache.derby.client.net.NetDatabaseMetaData
     */
    public org.apache.derby.client.am.DatabaseMetaData newNetDatabaseMetaData(Agent netAgent,
            org.apache.derby.client.am.Connection netConnection) {
        return new NetDatabaseMetaData((NetAgent)netAgent,
                (NetConnection)netConnection);
    }
}
