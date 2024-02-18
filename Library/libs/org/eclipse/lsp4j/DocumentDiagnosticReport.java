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

import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * The result of a document diagnostic pull request. A report can
 * either be a full report containing all diagnostics for the
 * requested document or a unchanged report indicating that nothing
 * has changed in terms of diagnostics in comparison to the last
 * pull request.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DocumentDiagnosticReport extends Either<RelatedFullDocumentDiagnosticReport, RelatedUnchangedDocumentDiagnosticReport> {
  public DocumentDiagnosticReport(@NonNull final RelatedFullDocumentDiagnosticReport relatedFullDocumentDiagnosticReport) {
    super(Preconditions.<RelatedFullDocumentDiagnosticReport>checkNotNull(relatedFullDocumentDiagnosticReport, "relatedFullDocumentDiagnosticReport"), null);
  }

  public DocumentDiagnosticReport(@NonNull final RelatedUnchangedDocumentDiagnosticReport relatedUnchangedDocumentDiagnosticReport) {
    super(null, Preconditions.<RelatedUnchangedDocumentDiagnosticReport>checkNotNull(relatedUnchangedDocumentDiagnosticReport, "relatedUnchangedDocumentDiagnosticReport"));
  }

  public RelatedFullDocumentDiagnosticReport getRelatedFullDocumentDiagnosticReport() {
    return super.getLeft();
  }

  public boolean isRelatedFullDocumentDiagnosticReport() {
    return super.isLeft();
  }

  public RelatedUnchangedDocumentDiagnosticReport getRelatedUnchangedDocumentDiagnosticReport() {
    return super.getRight();
  }

  public boolean isRelatedUnchangedDocumentDiagnosticReport() {
    return super.isRight();
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("left", getLeft());
    b.add("right", getRight());
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
    return true;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
