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
 * Cancellation data returned from a diagnostic request.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DiagnosticServerCancellationData {
  private boolean retriggerRequest;

  public DiagnosticServerCancellationData() {
  }

  public DiagnosticServerCancellationData(final boolean retriggerRequest) {
    this.retriggerRequest = retriggerRequest;
  }

  public boolean isRetriggerRequest() {
    return this.retriggerRequest;
  }

  public void setRetriggerRequest(final boolean retriggerRequest) {
    this.retriggerRequest = retriggerRequest;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("retriggerRequest", this.retriggerRequest);
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
    DiagnosticServerCancellationData other = (DiagnosticServerCancellationData) obj;
    if (other.retriggerRequest != this.retriggerRequest)
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + (this.retriggerRequest ? 1231 : 1237);
  }
}
