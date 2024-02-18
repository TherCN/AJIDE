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

import com.google.gson.annotations.JsonAdapter;
import java.util.List;
import org.eclipse.lsp4j.adapters.WorkspaceDocumentDiagnosticReportListAdapter;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A partial result for a workspace diagnostic report.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class WorkspaceDiagnosticReportPartialResult {
  @NonNull
  @JsonAdapter(WorkspaceDocumentDiagnosticReportListAdapter.class)
  private List<WorkspaceDocumentDiagnosticReport> items;

  public WorkspaceDiagnosticReportPartialResult() {
  }

  public WorkspaceDiagnosticReportPartialResult(@NonNull final List<WorkspaceDocumentDiagnosticReport> items) {
    this.items = Preconditions.<List<WorkspaceDocumentDiagnosticReport>>checkNotNull(items, "items");
  }

  @NonNull
  public List<WorkspaceDocumentDiagnosticReport> getItems() {
    return this.items;
  }

  public void setItems(@NonNull final List<WorkspaceDocumentDiagnosticReport> items) {
    this.items = Preconditions.checkNotNull(items, "items");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("items", this.items);
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
    WorkspaceDiagnosticReportPartialResult other = (WorkspaceDiagnosticReportPartialResult) obj;
    if (this.items == null) {
      if (other.items != null)
        return false;
    } else if (!this.items.equals(other.items))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.items== null) ? 0 : this.items.hashCode());
  }
}
