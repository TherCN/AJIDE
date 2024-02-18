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
 * A diagnostic report indicating that the last returned
 * report is still accurate.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class UnchangedDocumentDiagnosticReport {
  /**
   * A document diagnostic report indicating
   * no changes to the last result. A server can
   * only return `unchanged` if result ids are
   * provided.
   */
  @NonNull
  private final String kind = DocumentDiagnosticReportKind.Unchanged;

  /**
   * A result id which will be sent on the next
   * diagnostic request for the same document.
   */
  @NonNull
  private String resultId;

  public UnchangedDocumentDiagnosticReport() {
  }

  public UnchangedDocumentDiagnosticReport(@NonNull final String resultId) {
    this.resultId = Preconditions.<String>checkNotNull(resultId, "resultId");
  }

  /**
   * A document diagnostic report indicating
   * no changes to the last result. A server can
   * only return `unchanged` if result ids are
   * provided.
   */
  @NonNull
  public String getKind() {
    return this.kind;
  }

  /**
   * A result id which will be sent on the next
   * diagnostic request for the same document.
   */
  @NonNull
  public String getResultId() {
    return this.resultId;
  }

  /**
   * A result id which will be sent on the next
   * diagnostic request for the same document.
   */
  public void setResultId(@NonNull final String resultId) {
    this.resultId = Preconditions.checkNotNull(resultId, "resultId");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("kind", this.kind);
    b.add("resultId", this.resultId);
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
    UnchangedDocumentDiagnosticReport other = (UnchangedDocumentDiagnosticReport) obj;
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
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.kind== null) ? 0 : this.kind.hashCode());
    return prime * result + ((this.resultId== null) ? 0 : this.resultId.hashCode());
  }
}
