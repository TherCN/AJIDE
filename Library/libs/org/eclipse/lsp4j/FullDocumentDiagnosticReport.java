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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A diagnostic report with a full set of problems.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class FullDocumentDiagnosticReport {
  /**
   * A full document diagnostic report.
   */
  @NonNull
  private final String kind = DocumentDiagnosticReportKind.Full;

  /**
   * An optional result id. If provided it will
   * be sent on the next diagnostic request for the
   * same document.
   */
  private String resultId;

  /**
   * The actual items.
   */
  @NonNull
  private List<Diagnostic> items;

  public FullDocumentDiagnosticReport() {
    ArrayList<Diagnostic> _arrayList = new ArrayList<Diagnostic>();
    this.items = _arrayList;
  }

  public FullDocumentDiagnosticReport(@NonNull final List<Diagnostic> items) {
    this.items = Preconditions.<List<Diagnostic>>checkNotNull(items, "items");
  }

  /**
   * A full document diagnostic report.
   */
  @NonNull
  public String getKind() {
    return this.kind;
  }

  /**
   * An optional result id. If provided it will
   * be sent on the next diagnostic request for the
   * same document.
   */
  public String getResultId() {
    return this.resultId;
  }

  /**
   * An optional result id. If provided it will
   * be sent on the next diagnostic request for the
   * same document.
   */
  public void setResultId(final String resultId) {
    this.resultId = resultId;
  }

  /**
   * The actual items.
   */
  @NonNull
  public List<Diagnostic> getItems() {
    return this.items;
  }

  /**
   * The actual items.
   */
  public void setItems(@NonNull final List<Diagnostic> items) {
    this.items = Preconditions.checkNotNull(items, "items");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("kind", this.kind);
    b.add("resultId", this.resultId);
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
    FullDocumentDiagnosticReport other = (FullDocumentDiagnosticReport) obj;
    if (this.kind == null) {
      if (other.kind != null)
        return false;
    } else if (!this.kind.equals(other.kind))
      return false;
    if (this.resultId == null) {
      if (other.resultId != null)
        return false;
    } else if (!this.resultId.equals(other.resultId))
      return false;
    if (this.items == null) {
      if (other.items != null)
        return false;
    } else if (!this.items.equals(other.items))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.kind== null) ? 0 : this.kind.hashCode());
    result = prime * result + ((this.resultId== null) ? 0 : this.resultId.hashCode());
    return prime * result + ((this.items== null) ? 0 : this.items.hashCode());
  }
}
