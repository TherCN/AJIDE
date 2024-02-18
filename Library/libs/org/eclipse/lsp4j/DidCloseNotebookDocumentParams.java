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
 * The params sent in a close notebook document notification.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DidCloseNotebookDocumentParams {
  /**
   * The notebook document that got closed.
   */
  @NonNull
  private NotebookDocumentIdentifier notebookDocument;

  /**
   * The text documents that represent the content
   * of a notebook cell that got closed.
   */
  @NonNull
  private List<TextDocumentIdentifier> cellTextDocuments;

  public DidCloseNotebookDocumentParams() {
  }

  public DidCloseNotebookDocumentParams(@NonNull final NotebookDocumentIdentifier notebookDocument, @NonNull final List<TextDocumentIdentifier> cellTextDocuments) {
    this.notebookDocument = Preconditions.<NotebookDocumentIdentifier>checkNotNull(notebookDocument, "notebookDocument");
    this.cellTextDocuments = Preconditions.<List<TextDocumentIdentifier>>checkNotNull(cellTextDocuments, "cellTextDocuments");
  }

  /**
   * The notebook document that got closed.
   */
  @NonNull
  public NotebookDocumentIdentifier getNotebookDocument() {
    return this.notebookDocument;
  }

  /**
   * The notebook document that got closed.
   */
  public void setNotebookDocument(@NonNull final NotebookDocumentIdentifier notebookDocument) {
    this.notebookDocument = Preconditions.checkNotNull(notebookDocument, "notebookDocument");
  }

  /**
   * The text documents that represent the content
   * of a notebook cell that got closed.
   */
  @NonNull
  public List<TextDocumentIdentifier> getCellTextDocuments() {
    return this.cellTextDocuments;
  }

  /**
   * The text documents that represent the content
   * of a notebook cell that got closed.
   */
  public void setCellTextDocuments(@NonNull final List<TextDocumentIdentifier> cellTextDocuments) {
    this.cellTextDocuments = Preconditions.checkNotNull(cellTextDocuments, "cellTextDocuments");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("notebookDocument", this.notebookDocument);
    b.add("cellTextDocuments", this.cellTextDocuments);
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
    DidCloseNotebookDocumentParams other = (DidCloseNotebookDocumentParams) obj;
    if (this.notebookDocument == null) {
      if (other.notebookDocument != null)
        return false;
    } else if (!this.notebookDocument.equals(other.notebookDocument))
      return false;
    if (this.cellTextDocuments == null) {
      if (other.cellTextDocuments != null)
        return false;
    } else if (!this.cellTextDocuments.equals(other.cellTextDocuments))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.notebookDocument== null) ? 0 : this.notebookDocument.hashCode());
    return prime * result + ((this.cellTextDocuments== null) ? 0 : this.cellTextDocuments.hashCode());
  }
}
