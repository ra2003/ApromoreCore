/**
 * Copyright 2012, Felix Mannhardt
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.apromore.plugin.deployment.impl;

import org.apromore.plugin.deployment.DeploymentPlugin;
import org.apromore.plugin.impl.DefaultPropertyAwarePlugin;

/**
 * Default implementation of the Deployment Plugin.
 *
 * @author <a href="mailto:felix.mannhardt@smail.wir.h-brs.de">Felix Mannhardt</a>
 *
 */
public abstract class DefaultDeploymentPlugin extends DefaultPropertyAwarePlugin implements DeploymentPlugin {

    /*
     * (non-Javadoc)
     *
     * @see org.apromore.plugin.deployment.DeploymentPlugin#getNativeType()
     */
    @Override
    public String getNativeType() {
        return getConfigurationByName("deployment.nativeType");
    }

}