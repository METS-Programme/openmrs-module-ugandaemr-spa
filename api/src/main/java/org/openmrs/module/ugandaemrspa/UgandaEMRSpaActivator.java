/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.ugandaemrspa;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.initializer.api.InitializerService;
import org.openmrs.util.OpenmrsUtil;

import java.io.File;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class UgandaEMRSpaActivator extends BaseModuleActivator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * @see #started()
	 */
	public void started() {
		log.info("Started UgandaEMRSpa");
		
		File source = FileUtils.toFile(getClass().getClassLoader().getResource("configuration"));
		
		File dest = new File(OpenmrsUtil.getApplicationDataDirectory() + "configuration");
		try {
			FileUtils.copyDirectory(source, dest);
			InitializerService initializerService = Context.getService(InitializerService.class);
			initializerService.loadUnsafe(false, true);
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * @see #shutdown()
	 */
	public void shutdown() {
		log.info("Shutdown UgandaEMRSpa");
	}
	
}
