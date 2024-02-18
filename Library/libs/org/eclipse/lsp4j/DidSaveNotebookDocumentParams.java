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
 * The params sent in a save notebook document notification.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DidSaveNotebookDocumentParams {
  /**
   * The notebook document that got saved.
   */
  @NonNull
  private NotebookDocumentIdentifier notebookDocument;

  public DidSaveNotebookDocumentParams() {
  }

  public DidSaveNotebookDocumentParams(@NonNull final NotebookDocumentIdentifier notebookDocument) {
    this.notebookDocument = Preconditions.<NotebookDocumentIdentifier>checkNotNull(notebookDocument, "notebookDocument");
  }

  /**
   * The notebook document that got saved.
   */
  @NonNull
  public NotebookDocumentIdentifier getNotebookDocument() {
    return this.notebookDocument;
  }

  /**
   * The notebook document that got saved.
   */
  public void setNotebookDocument(@NonNull final NotebookDocumentIdentifier notebookDocument) {
    this.notebookDocument = Preconditions.checkNotNull(notebookDocument, "notebookDocument");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("notebookDocument", this.notebookDocument);
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
    DidSaveNotebookDocumentParams other = (DidSaveNotebookDocumentParams) obj;
    if (this.notebookDocument == null) {
      if (other.notebookDocument != null)
        return false;
    } else if (!this.notebookDocument.equals(other.notebookDocument))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.notebookDocument== null) ? 0 : this.notebookDocument.hashCode());
  }
}
