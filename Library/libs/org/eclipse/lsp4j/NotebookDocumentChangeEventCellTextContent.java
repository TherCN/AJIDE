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
 * Changes to the text content of notebook cells.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookDocumentChangeEventCellTextContent {
  @NonNull
  private VersionedTextDocumentIdentifier document;

  @NonNull
  private List<TextDocumentContentChangeEvent> changes;

  public NotebookDocumentChangeEventCellTextContent() {
    ArrayList<TextDocumentContentChangeEvent> _arrayList = new ArrayList<TextDocumentContentChangeEvent>();
    this.changes = _arrayList;
  }

  public NotebookDocumentChangeEventCellTextContent(@NonNull final VersionedTextDocumentIdentifier document, @NonNull final List<TextDocumentContentChangeEvent> changes) {
    this.document = Preconditions.<VersionedTextDocumentIdentifier>checkNotNull(document, "document");
    this.changes = Preconditions.<List<TextDocumentContentChangeEvent>>checkNotNull(changes, "changes");
  }

  @NonNull
  public VersionedTextDocumentIdentifier getDocument() {
    return this.document;
  }

  public void setDocument(@NonNull final VersionedTextDocumentIdentifier document) {
    this.document = Preconditions.checkNotNull(document, "document");
  }

  @NonNull
  public List<TextDocumentContentChangeEvent> getChanges() {
    return this.changes;
  }

  public void setChanges(@NonNull final List<TextDocumentContentChangeEvent> changes) {
    this.changes = Preconditions.checkNotNull(changes, "changes");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("document", this.document);
    b.add("changes", this.changes);
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
    NotebookDocumentChangeEventCellTextContent other = (NotebookDocumentChangeEventCellTextContent) obj;
    if (this.document == null) {
      if (other.document != null)
        return false;
    } else if (!this.document.equals(other.document))
      return false;
    if (this.changes == null) {
      if (other.changes != null)
        return false;
    } else if (!this.changes.equals(other.changes))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.document== null) ? 0 : this.document.hashCode());
    return prime * result + ((this.changes== null) ? 0 : this.changes.hashCode());
  }
}
