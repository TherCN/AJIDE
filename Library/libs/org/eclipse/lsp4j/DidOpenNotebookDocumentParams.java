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
 * The params sent in an open notebook document notification.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DidOpenNotebookDocumentParams {
  /**
   * The notebook document that got opened.
   */
  @NonNull
  private NotebookDocument notebookDocument;

  /**
   * The text documents that represent the content
   * of a notebook cell.
   */
  @NonNull
  private List<TextDocumentItem> cellTextDocuments;

  public DidOpenNotebookDocumentParams() {
    ArrayList<TextDocumentItem> _arrayList = new ArrayList<TextDocumentItem>();
    this.cellTextDocuments = _arrayList;
  }

  public DidOpenNotebookDocumentParams(@NonNull final NotebookDocument notebookDocument, @NonNull final List<TextDocumentItem> cellTextDocuments) {
    this.notebookDocument = Preconditions.<NotebookDocument>checkNotNull(notebookDocument, "notebookDocument");
    this.cellTextDocuments = Preconditions.<List<TextDocumentItem>>checkNotNull(cellTextDocuments, "cellTextDocuments");
  }

  /**
   * The notebook document that got opened.
   */
  @NonNull
  public NotebookDocument getNotebookDocument() {
    return this.notebookDocument;
  }

  /**
   * The notebook document that got opened.
   */
  public void setNotebookDocument(@NonNull final NotebookDocument notebookDocument) {
    this.notebookDocument = Preconditions.checkNotNull(notebookDocument, "notebookDocument");
  }

  /**
   * The text documents that represent the content
   * of a notebook cell.
   */
  @NonNull
  public List<TextDocumentItem> getCellTextDocuments() {
    return this.cellTextDocuments;
  }

  /**
   * The text documents that represent the content
   * of a notebook cell.
   */
  public void setCellTextDocuments(@NonNull final List<TextDocumentItem> cellTextDocuments) {
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
    DidOpenNotebookDocumentParams other = (DidOpenNotebookDocumentParams) obj;
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
