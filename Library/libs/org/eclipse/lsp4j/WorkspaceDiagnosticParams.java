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

import java.util.List;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Parameters of the workspace diagnostic request.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class WorkspaceDiagnosticParams extends WorkDoneProgressAndPartialResultParams {
  /**
   * The additional identifier provided during registration.
   */
  private String identifier;

  /**
   * The currently known diagnostic reports with their
   * previous result ids.
   */
  @NonNull
  private List<PreviousResultId> previousResultIds;

  public WorkspaceDiagnosticParams() {
  }

  public WorkspaceDiagnosticParams(@NonNull final List<PreviousResultId> previousResultIds) {
    this.previousResultIds = Preconditions.<List<PreviousResultId>>checkNotNull(previousResultIds, "previousResultIds");
  }

  /**
   * The additional identifier provided during registration.
   */
  public String getIdentifier() {
    return this.identifier;
  }

  /**
   * The additional identifier provided during registration.
   */
  public void setIdentifier(final String identifier) {
    this.identifier = identifier;
  }

  /**
   * The currently known diagnostic reports with their
   * previous result ids.
   */
  @NonNull
  public List<PreviousResultId> getPreviousResultIds() {
    return this.previousResultIds;
  }

  /**
   * The currently known diagnostic reports with their
   * previous result ids.
   */
  public void setPreviousResultIds(@NonNull final List<PreviousResultId> previousResultIds) {
    this.previousResultIds = Preconditions.checkNotNull(previousResultIds, "previousResultIds");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("identifier", this.identifier);
    b.add("previousResultIds", this.previousResultIds);
    b.add("workDoneToken", getWorkDoneToken());
    b.add("partialResultToken", getPartialResultToken());
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
    WorkspaceDiagnosticParams other = (WorkspaceDiagnosticParams) obj;
    if (this.identifier == null) {
      if (other.identifier != null)
        return false;
    } else if (!this.identifier.equals(other.identifier))
      return false;
    if (this.previousResultIds == null) {
      if (other.previousResultIds != null)
        return false;
    } else if (!this.previousResultIds.equals(other.previousResultIds))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.identifier== null) ? 0 : this.identifier.hashCode());
    return prime * result + ((this.previousResultIds== null) ? 0 : this.previousResultIds.hashCode());
  }
}
