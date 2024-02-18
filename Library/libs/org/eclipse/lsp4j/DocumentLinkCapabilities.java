/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Capabilities specific to the `textDocument/documentLink`
 */
@SuppressWarnings("all")
public class DocumentLinkCapabilities extends DynamicRegistrationCapabilities {
  /**
   * Whether the client supports the {@link DocumentLink#tooltip} property.
   * <p>
   * Since 3.15.0
   */
  private Boolean tooltipSupport;

  public DocumentLinkCapabilities() {
  }

  public DocumentLinkCapabilities(final Boolean dynamicRegistration) {
    super(dynamicRegistration);
  }

  public DocumentLinkCapabilities(final Boolean dynamicRegistration, final Boolean tooltipSupport) {
    super(dynamicRegistration);
    this.tooltipSupport = tooltipSupport;
  }

  /**
   * Whether the client supports the {@link DocumentLink#tooltip} property.
   * <p>
   * Since 3.15.0
   */
  public Boolean getTooltipSupport() {
    return this.tooltipSupport;
  }

  /**
   * Whether the client supports the {@link DocumentLink#tooltip} property.
   * <p>
   * Since 3.15.0
   */
  public void setTooltipSupport(final Boolean tooltipSupport) {
    this.tooltipSupport = tooltipSupport;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("tooltipSupport", this.tooltipSupport);
    b.add("dynamicRegistration", getDynamicRegistration());
    return b.toString();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    if (!super.equals(obj))
      return false;
    DocumentLinkCapabilities other = (DocumentLinkCapabilities) obj;
    if (this.tooltipSupport == null) {
      if (other.tooltipSupport != null)
        return false;
    } else if (!this.tooltipSupport.equals(other.tooltipSupport))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + ((this.tooltipSupport== null) ? 0 : this.tooltipSupport.hashCode());
  }
}
