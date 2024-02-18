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
 * The params sent in a change notebook document notification.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class DidChangeNotebookDocumentParams {
  /**
   * The notebook document that did change. The version number points
   * to the version after all provided changes have been applied.
   */
  @NonNull
  private VersionedNotebookDocumentIdentifier notebookDocument;

  /**
   * The actual changes to the notebook document.
   * <p>
   * The change describes single state change to the notebook document.
   * So it moves a notebook document, its cells and its cell text document
   * contents from state S to S'.
   * <p>
   * To mirror the content of a notebook using change events use the
   * following approach:
   * <p><ul>
   * <li>start with the same initial content
   * <li>apply the 'notebookDocument/didChange' notifications in the order
   * you receive them.
   * </ul>
   */
  @NonNull
  private NotebookDocumentChangeEvent change;

  public DidChangeNotebookDocumentParams() {
  }

  public DidChangeNotebookDocumentParams(@NonNull final VersionedNotebookDocumentIdentifier notebookDocument, @NonNull final NotebookDocumentChangeEvent change) {
    this.notebookDocument = Preconditions.<VersionedNotebookDocumentIdentifier>checkNotNull(notebookDocument, "notebookDocument");
    this.change = Preconditions.<NotebookDocumentChangeEvent>checkNotNull(change, "change");
  }

  /**
   * The notebook document that did change. The version number points
   * to the version after all provided changes have been applied.
   */
  @NonNull
  public VersionedNotebookDocumentIdentifier getNotebookDocument() {
    return this.notebookDocument;
  }

  /**
   * The notebook document that did change. The version number points
   * to the version after all provided changes have been applied.
   */
  public void setNotebookDocument(@NonNull final VersionedNotebookDocumentIdentifier notebookDocument) {
    this.notebookDocument = Preconditions.checkNotNull(notebookDocument, "notebookDocument");
  }

  /**
   * The actual changes to the notebook document.
   * <p>
   * The change describes single state change to the notebook document.
   * So it moves a notebook document, its cells and its cell text document
   * contents from state S to S'.
   * <p>
   * To mirror the content of a notebook using change events use the
   * following approach:
   * <p><ul>
   * <li>start with the same initial content
   * <li>apply the 'notebookDocument/didChange' notifications in the order
   * you receive them.
   * </ul>
   */
  @NonNull
  public NotebookDocumentChangeEvent getChange() {
    return this.change;
  }

  /**
   * The actual changes to the notebook document.
   * <p>
   * The change describes single state change to the notebook document.
   * So it moves a notebook document, its cells and its cell text document
   * contents from state S to S'.
   * <p>
   * To mirror the content of a notebook using change events use the
   * following approach:
   * <p><ul>
   * <li>start with the same initial content
   * <li>apply the 'notebookDocument/didChange' notifications in the order
   * you receive them.
   * </ul>
   */
  public void setChange(@NonNull final NotebookDocumentChangeEvent change) {
    this.change = Preconditions.checkNotNull(change, "change");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("notebookDocument", this.notebookDocument);
    b.add("change", this.change);
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
    DidChangeNotebookDocumentParams other = (DidChangeNotebookDocumentParams) obj;
    if (this.notebookDocument == null) {
      if (other.notebookDocument != null)
        return false;
    } else if (!this.notebookDocument.equals(other.notebookDocument))
      return false;
    if (this.change == null) {
      if (other.change != null)
        return false;
    } else if (!this.change.equals(other.change))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.notebookDocument== null) ? 0 : this.notebookDocument.hashCode());
    return prime * result + ((this.change== null) ? 0 : this.change.hashCode());
  }
}
