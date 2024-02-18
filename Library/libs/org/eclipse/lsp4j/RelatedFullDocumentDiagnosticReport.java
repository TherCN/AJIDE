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
import java.util.Map;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A full diagnostic report with a set of related documents.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class RelatedFullDocumentDiagnosticReport extends FullDocumentDiagnosticReport {
  /**
   * Diagnostics of related documents. This information is useful
   * in programming languages where code in a file A can generate
   * diagnostics in a file B which A depends on. An example of
   * such a language is C/C++ where marco definitions in a file
   * a.cpp and result in errors in a header file b.hpp.
   */
  private Map<String, Either<FullDocumentDiagnosticReport, UnchangedDocumentDiagnosticReport>> relatedDocuments;

  public RelatedFullDocumentDiagnosticReport() {
  }

  public RelatedFullDocumentDiagnosticReport(@NonNull final List<Diagnostic> items) {
    super(items);
  }

  /**
   * Diagnostics of related documents. This information is useful
   * in programming languages where code in a file A can generate
   * diagnostics in a file B which A depends on. An example of
   * such a language is C/C++ where marco definitions in a file
   * a.cpp and result in errors in a header file b.hpp.
   */
  public Map<String, Either<FullDocumentDiagnosticReport, UnchangedDocumentDiagnosticReport>> getRelatedDocuments() {
    return this.relatedDocuments;
  }

  /**
   * Diagnostics of related documents. This information is useful
   * in programming languages where code in a file A can generate
   * diagnostics in a file B which A depends on. An example of
   * such a language is C/C++ where marco definitions in a file
   * a.cpp and result in errors in a header file b.hpp.
   */
  public void setRelatedDocuments(final Map<String, Either<FullDocumentDiagnosticReport, UnchangedDocumentDiagnosticReport>> relatedDocuments) {
    this.relatedDocuments = relatedDocuments;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("relatedDocuments", this.relatedDocuments);
    b.add("kind", getKind());
    b.add("resultId", getResultId());
    b.add("items", getItems());
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
    RelatedFullDocumentDiagnosticReport other = (RelatedFullDocumentDiagnosticReport) obj;
    if (this.relatedDocuments == null) {
      if (other.relatedDocuments != null)
        return false;
    } else if (!this.relatedDocuments.equals(other.relatedDocuments))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + ((this.relatedDocuments== null) ? 0 : this.relatedDocuments.hashCode());
  }
}
