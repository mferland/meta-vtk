DESCRIPTION = "The HDF5 library (Hierarchical Data Format)"
HOMEPAGE = "http://www.hdfgroup.org"
SECTION = "libs"
LICENSE = "license_hdf5"
LIC_FILES_CHKSUM = "file://COPYING;md5=4ad878f669af233d3b861433c71857e4"

SRC_URI = "http://www.hdfgroup.org/ftp/HDF5/current/src/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "03ad766d225f5e872eb3e5ce95524a08"
SRC_URI[sha256sum] = "6d080f913a226a3ce390a11d9b571b2d5866581a2aa4434c398cd371c7063639"

inherit autotools pkgconfig
