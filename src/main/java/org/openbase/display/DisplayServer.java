package org.openbase.display;

/*
 * #%L
 * GenericDisplay
 * %%
 * Copyright (C) 2015 - 2021 openbase.org
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import org.openbase.jul.communication.controller.AbstractControllerServer;
import org.openbase.jul.communication.controller.RPCHelper;
import org.openbase.jul.exception.CouldNotPerformException;
import org.openbase.jul.exception.InstantiationException;
import org.openbase.type.domotic.unit.dal.DisplayDataType.DisplayData.Builder;
import rsb.converter.DefaultConverterRepository;
import rsb.converter.ProtocolBufferConverter;
import org.openbase.type.configuration.MetaConfigType.MetaConfig;
import org.openbase.jul.extension.rsb.iface.RSBLocalServer;
import org.openbase.type.domotic.unit.dal.DisplayDataType.DisplayData;

/**
 *
 * @author <a href="mailto:divine@openbase.org">Divine Threepwood</a>
 */
public class DisplayServer extends AbstractControllerServer<DisplayData, Builder> {

    static {
        DefaultConverterRepository.getDefaultConverterRepository().addConverter(new ProtocolBufferConverter<>(MetaConfig.getDefaultInstance()));
        DefaultConverterRepository.getDefaultConverterRepository().addConverter(new ProtocolBufferConverter<>(DisplayData.getDefaultInstance()));
    }

    private final Display display;

    public DisplayServer(final Display display) throws InstantiationException, CouldNotPerformException {
        super(DisplayData.newBuilder());
        this.display = display;
    }

    /**
     * {@inheritDoc}
     *
     * @param server
     * @throws org.openbase.jul.exception.CouldNotPerformException
     */
    @Override
    public void registerMethods(final RSBLocalServer server) throws CouldNotPerformException {
        RPCHelper.registerInterface(Display.class, display, server);
    }
}
