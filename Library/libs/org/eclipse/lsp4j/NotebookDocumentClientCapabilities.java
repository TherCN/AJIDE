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

import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Capabilities specific to the notebook document support.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookDocumentClientCapabilities {
  /**
   * Capabilities specific to notebook document synchronization
   */
  @NonNull
  private NotebookDocumentSyncClientCapabilities synchronization;

  public NotebookDocumentClientCapabilities() {
  }

  public NotebookDocumentClientCapabilities(@NonNull final NotebookDocumentSyncClientCapabilities synchronization) {
    this.synchronization = Preconditions.<NotebookDocumentSyncClientCapabilities>checkNotNull(synchronization, "synchronization");
  }

  /**
   * Capabilities specific to notebook document synchronization
   */
  @NonNull
  public NotebookDocumentSyncClientCapabilities getSynchronization() {
    return this.synchronization;
  }

  /**
   * Capabilities specific to notebook document synchronization
   */
  public void setSynchronization(@NonNull final NotebookDocumentSyncClientCapabilities synchronization) {
    this.synchronization = Preconditions.checkNotNull(synchronization, "synchronization");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("synchronization", this.synchronization);
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
    NotebookDocumentClientCapabilities other = (NotebookDocumentClientCapabilities) obj;
    if (this.synchronization == null) {
      if (other.synchronization != null)
        return false;
    } else if (!this.synchronization.equals(other.synchronization))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.synchronization== null) ? 0 : this.synchronization.hashCode());
  }
}
