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
 * Notebook specific client capabilities.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookDocumentSyncClientCapabilities extends DynamicRegistrationCapabilities {
  /**
   * The client supports sending execution summary data per cell.
   */
  private Boolean executionSummarySupport;

  public NotebookDocumentSyncClientCapabilities() {
  }

  public NotebookDocumentSyncClientCapabilities(final Boolean dynamicRegistration) {
    super(dynamicRegistration);
  }

  public NotebookDocumentSyncClientCapabilities(final Boolean dynamicRegistration, final Boolean executionSummarySupport) {
    this(dynamicRegistration);
    this.executionSummarySupport = executionSummarySupport;
  }

  /**
   * The client supports sending execution summary data per cell.
   */
  public Boolean getExecutionSummarySupport() {
    return this.executionSummarySupport;
  }

  /**
   * The client supports sending execution summary data per cell.
   */
  public void setExecutionSummarySupport(final Boolean executionSummarySupport) {
    this.executionSummarySupport = executionSummarySupport;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("executionSummarySupport", this.executionSummarySupport);
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
    NotebookDocumentSyncClientCapabilities other = (NotebookDocumentSyncClientCapabilities) obj;
    if (this.executionSummarySupport == null) {
      if (other.executionSummarySupport != null)
        return false;
    } else if (!this.executionSummarySupport.equals(other.executionSummarySupport))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + ((this.executionSummarySupport== null) ? 0 : this.executionSummarySupport.hashCode());
  }
}
