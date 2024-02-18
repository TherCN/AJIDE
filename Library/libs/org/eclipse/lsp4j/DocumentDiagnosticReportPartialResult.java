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

import java.util.Map;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A partial result for a document diagnostic report.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DocumentDiagnosticReportPartialResult {
  @NonNull
  private Map<String, Either<FullDocumentDiagnosticReport, UnchangedDocumentDiagnosticReport>> relatedDocuments;

  public DocumentDiagnosticReportPartialResult(@NonNull final Map<String, Either<FullDocumentDiagnosticReport, UnchangedDocumentDiagnosticReport>> relatedDocuments) {
    this.relatedDocuments = Preconditions.<Map<String, Either<FullDocumentDiagnosticReport, UnchangedDocumentDiagnosticReport>>>checkNotNull(relatedDocuments, "relatedDocuments");
  }

  @NonNull
  public Map<String, Either<FullDocumentDiagnosticReport, UnchangedDocumentDiagnosticReport>> getRelatedDocuments() {
    return this.relatedDocuments;
  }

  public void setRelatedDocuments(@NonNull final Map<String, Either<FullDocumentDiagnosticReport, UnchangedDocumentDiagnosticReport>> relatedDocuments) {
    this.relatedDocuments = Preconditions.checkNotNull(relatedDocuments, "relatedDocuments");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("relatedDocuments", this.relatedDocuments);
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
    DocumentDiagnosticReportPartialResult other = (DocumentDiagnosticReportPartialResult) obj;
    if (this.relatedDocuments == null) {
      if (other.relatedDocuments != null)
        return false;
    } else if (!this.relatedDocuments.equals(other.relatedDocuments))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.relatedDocuments== null) ? 0 : this.relatedDocuments.hashCode());
  }
}
