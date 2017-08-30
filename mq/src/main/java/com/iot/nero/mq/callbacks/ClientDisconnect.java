/*
 * Copyright 2015 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iot.nero.mq.callbacks;

import com.hivemq.spi.callback.events.OnDisconnectCallback;
import com.hivemq.spi.security.ClientData;
import com.iot.nero.mq.Consumer;
import com.iot.nero.mq.dto.ClientConn;
import com.iot.nero.mq.dto.Result;
import com.iot.nero.service.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the OnDisconnectCallback, which is invoke everytime a client disconnects.
 * The callback allows to implement custom logic, which should be executed after a disconnect.
 *
 * @author Christian Götz
 */
public class ClientDisconnect implements OnDisconnectCallback {

    Logger log = LoggerFactory.getLogger(ClientDisconnect.class);
    private IClientService clientService;

    /**
     * This method is called from the HiveMQ on a client disconnect.
     *
     * @param clientData       Useful information about the clients authentication state and credentials.
     * @param abruptDisconnect When true the connection of the client broke down without a
     *                         {@link com.hivemq.spi.message.DISCONNECT} message and if false then the client
     *                         disconnected properly with a {@link com.hivemq.spi.message.DISCONNECT} message.
     */
    @Override
    public void onDisconnect(ClientData clientData, boolean abruptDisconnect) {
        log.info("Client {} is disconnected", clientData.getClientId());
        try{
            clientService = (IClientService) Consumer.singleton().getBean("IClientService");
            String productKey = clientData.getUsername().get();
            String clientId = clientData.getClientId();
            Result<ClientConn> result = clientService.clientOffLine(productKey,clientId);
            System.out.println(result);
            if(result.isState()){
                log.info("offline success!!!");
            }else{
                log.info("offline failed!!!");
            }
        }catch (Exception e){
            log.info("Client service exception！");
        }
    }
}
