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
 * Changes to the cell structure to add or
 * remove cells.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookDocumentChangeEventCellStructure {
  /**
   * The change to the cell array.
   */
  @NonNull
  private NotebookCellArrayChange array;

  /**
   * Additional opened cell text documents.
   */
  private List<TextDocumentItem> didOpen;

  /**
   * Additional closed cell text documents.
   */
  private List<TextDocumentIdentifier> didClose;

  public NotebookDocumentChangeEventCellStructure() {
  }

  public NotebookDocumentChangeEventCellStructure(@NonNull final NotebookCellArrayChange array) {
    this.array = Preconditions.<NotebookCellArrayChange>checkNotNull(array, "array");
  }

  /**
   * The change to the cell array.
   */
  @NonNull
  public NotebookCellArrayChange getArray() {
    return this.array;
  }

  /**
   * The change to the cell array.
   */
  public void setArray(@NonNull final NotebookCellArrayChange array) {
    this.array = Preconditions.checkNotNull(array, "array");
  }

  /**
   * Additional opened cell text documents.
   */
  public List<TextDocumentItem> getDidOpen() {
    return this.didOpen;
  }

  /**
   * Additional opened cell text documents.
   */
  public void setDidOpen(final List<TextDocumentItem> didOpen) {
    this.didOpen = didOpen;
  }

  /**
   * Additional closed cell text documents.
   */
  public List<TextDocumentIdentifier> getDidClose() {
    return this.didClose;
  }

  /**
   * Additional closed cell text documents.
   */
  public void setDidClose(final List<TextDocumentIdentifier> didClose) {
    this.didClose = didClose;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("array", this.array);
    b.add("didOpen", this.didOpen);
    b.add("didClose", this.didClose);
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
    NotebookDocumentChangeEventCellStructure other = (NotebookDocumentChangeEventCellStructure) obj;
    if (this.array == null) {
      if (other.array != null)
        return false;
    } else if (!this.array.equals(other.array))
      return false;
    if (this.didOpen == null) {
      if (other.didOpen != null)
        return false;
    } else if (!this.didOpen.equals(other.didOpen))
      return false;
    if (this.didClose == null) {
      if (other.didClose != null)
        return false;
    } else if (!this.didClose.equals(other.didClose))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.array== null) ? 0 : this.array.hashCode());
    result = prime * result + ((this.didOpen== null) ? 0 : this.didOpen.hashCode());
    return prime * result + ((this.didClose== null) ? 0 : this.didClose.hashCode());
  }
}
