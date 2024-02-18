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
 * Parameters of the document diagnostic request.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DocumentDiagnosticParams extends WorkDoneProgressAndPartialResultParams {
  /**
   * The text document.
   */
  @NonNull
  private TextDocumentIdentifier textDocument;

  /**
   * The additional identifier provided during registration.
   */
  private String identifier;

  /**
   * The result id of a previous response if provided.
   */
  private String previousResultId;

  public DocumentDiagnosticParams() {
  }

  public DocumentDiagnosticParams(@NonNull final TextDocumentIdentifier textDocument) {
    this.textDocument = Preconditions.<TextDocumentIdentifier>checkNotNull(textDocument, "textDocument");
  }

  /**
   * The text document.
   */
  @NonNull
  public TextDocumentIdentifier getTextDocument() {
    return this.textDocument;
  }

  /**
   * The text document.
   */
  public void setTextDocument(@NonNull final TextDocumentIdentifier textDocument) {
    this.textDocument = Preconditions.checkNotNull(textDocument, "textDocument");
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
   * The result id of a previous response if provided.
   */
  public String getPreviousResultId() {
    return this.previousResultId;
  }

  /**
   * The result id of a previous response if provided.
   */
  public void setPreviousResultId(final String previousResultId) {
    this.previousResultId = previousResultId;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("textDocument", this.textDocument);
    b.add("identifier", this.identifier);
    b.add("previousResultId", this.previousResultId);
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
    DocumentDiagnosticParams other = (DocumentDiagnosticParams) obj;
    if (this.textDocument == null) {
      if (other.textDocument != null)
        return false;
    } else if (!this.textDocument.equals(other.textDocument))
      return false;
    if (this.identifier == null) {
      if (other.identifier != null)
        return false;
    } else if (!this.identifier.equals(other.identifier))
      return false;
    if (this.previousResultId == null) {
      if (other.previousResultId != null)
        return false;
    } else if (!this.previousResultId.equals(other.previousResultId))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.textDocument== null) ? 0 : this.textDocument.hashCode());
    result = prime * result + ((this.identifier== null) ? 0 : this.identifier.hashCode());
    return prime * result + ((this.previousResultId== null) ? 0 : this.previousResultId.hashCode());
  }
}
