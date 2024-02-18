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
 * Client capabilities specific to diagnostic pull requests.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DiagnosticCapabilities extends DynamicRegistrationCapabilities {
  /**
   * Whether the clients supports related documents for document diagnostic pulls.
   */
  private Boolean relatedDocumentSupport;

  public DiagnosticCapabilities() {
  }

  public DiagnosticCapabilities(final Boolean dynamicRegistration) {
    this.setDynamicRegistration(dynamicRegistration);
  }

  public DiagnosticCapabilities(final Boolean dynamicRegistration, final Boolean relatedDocumentSupport) {
    this(dynamicRegistration);
    this.relatedDocumentSupport = relatedDocumentSupport;
  }

  /**
   * Whether the clients supports related documents for document diagnostic pulls.
   */
  public Boolean getRelatedDocumentSupport() {
    return this.relatedDocumentSupport;
  }

  /**
   * Whether the clients supports related documents for document diagnostic pulls.
   */
  public void setRelatedDocumentSupport(final Boolean relatedDocumentSupport) {
    this.relatedDocumentSupport = relatedDocumentSupport;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("relatedDocumentSupport", this.relatedDocumentSupport);
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
    DiagnosticCapabilities other = (DiagnosticCapabilities) obj;
    if (this.relatedDocumentSupport == null) {
      if (other.relatedDocumentSupport != null)
        return false;
    } else if (!this.relatedDocumentSupport.equals(other.relatedDocumentSupport))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + ((this.relatedDocumentSupport== null) ? 0 : this.relatedDocumentSupport.hashCode());
  }
}
